package com.tixly.event_service.service.impl;

import com.tixly.event_service.dto.EventRequestDto;
import com.tixly.event_service.dto.EventResponseDto;
import com.tixly.event_service.entity.Event;
import com.tixly.event_service.entity.Image;
import com.tixly.event_service.entity.Organizer;
import com.tixly.event_service.service.EventService;
import com.tixly.event_service.exception.ApiException;
import com.tixly.event_service.exception.GeneralException;
import com.tixly.event_service.exception.ResourceNotFoundException;
import com.tixly.event_service.repository.EventRepository;
import com.tixly.event_service.repository.ImageRepository;
import com.tixly.event_service.repository.OrganizerRepository;
import com.tixly.event_service.util.ImageUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    @Transactional
    public EventResponseDto createEvent(EventRequestDto eventRequestDto) {
       try{
         Organizer organizer = getOrganizerById(eventRequestDto.getOrganizerId());
         Event event = createAndSaveEvent(eventRequestDto, organizer);
         List<Image> images = processAndSaveImages(eventRequestDto.getFiles(), event);
         return convertToEventResponseDto(images, event);
       } catch (Exception e){
           log.error("Error occurred: {}", e.getMessage());
           throw new GeneralException();
       }
    }
    @Override
    public EventResponseDto getEventById(Integer id) {
        try{
            Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "Event"));
            return modelMapper.map(event, EventResponseDto.class);
        } catch (ResourceNotFoundException e) {
            log.error("Failed to fetch Event with id {}: {}", id, e.getMessage());
            throw e;
        } catch (Exception e){
            log.error("Error occurred: {}", e.getMessage());
            throw new GeneralException();
        }
    }
    @Override
    public List<EventResponseDto> getAllEvents() {
        try {
            List<Event> events = eventRepository.findAll();
            return events.stream()
                    .map(event -> modelMapper.map(event, EventResponseDto.class)).collect(Collectors.toList());
        } catch (Exception e){
            log.error("Error occurred: {}", e.getMessage());
            throw new GeneralException();
        }
    }
    @Override
    public EventResponseDto updateEvent(Integer id, EventRequestDto eventRequestDto) {
        try{
            Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "Event"));
            Organizer organizer = getOrganizerById(eventRequestDto.getOrganizerId());
            event.setCategory(eventRequestDto.getCategory());
            event.setDescription(eventRequestDto.getDescription());
            event.setLocation(eventRequestDto.getLocation());
            event.setTitle(eventRequestDto.getTitle());
            event.setStartTime(eventRequestDto.getStartTime());
            event.setEndTime(eventRequestDto.getEndTime());
            event.setOrganizer(organizer);
            Event savedEvent = eventRepository.save(event);
            return modelMapper.map(savedEvent, EventResponseDto.class);
        } catch (ResourceNotFoundException e) {
            log.error("Failed to fetch Event with id {}: {}", id, e.getMessage());
            throw e;
        } catch (Exception e){
            log.error("Error occurred: {}", e.getMessage());
            throw new GeneralException();
        }
    }

    @Override
    public void deleteEvent(Integer id) {
        try {
            Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "Event"));
            eventRepository.delete(event);
        } catch (ResourceNotFoundException e) {
            log.error("Failed to fetch Event with id {}: {}", id, e.getMessage());
            throw e;
        } catch (Exception e){
            log.error("Error occurred: {}", e.getMessage());
            throw new GeneralException();
        }
    }
    private Organizer getOrganizerById(Integer organizerId) {
        try{
            return organizerRepository.findById(organizerId)
                    .orElseThrow(() -> new ResourceNotFoundException(organizerId, "Organizer"));
        } catch (ResourceNotFoundException e) {
            log.error("Failed to fetch Organizer with id {}: {}",organizerId, e.getMessage());
            throw e;
        }
    }
    private Event createAndSaveEvent(EventRequestDto dto, Organizer organizer) {
        Event event = modelMapper.map(dto, Event.class);
        event.setCreatedAt(LocalDateTime.now());
        event.setOrganizer(organizer);
        return eventRepository.save(event);
    }
    private List<Image> processAndSaveImages(List<MultipartFile> files, Event event) {
        if (files == null || files.isEmpty()) {
            return Collections.emptyList();
        }
        List<Image> imagesToSave = files.stream()
                .filter(Objects::nonNull)
                .filter(file -> !file.isEmpty())
                .map(file -> createImageEntity(file, event))
                .collect(Collectors.toList());

        List<Image> savedImages = imageRepository.saveAll(imagesToSave);

        if (savedImages.isEmpty() && !imagesToSave.isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Failed to save images");
        }
        return savedImages;
    }
    private Image createImageEntity(MultipartFile file, Event event) {
        try {
            return Image.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .event(event)
                    .image(ImageUtil.compressImage(file.getBytes()))
                    .build();
        } catch (IOException e) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "Failed to process image: " + file.getOriginalFilename());
        }
    }
    private EventResponseDto convertToEventResponseDto(List<Image> images , Event event){
        List<String> imageName =  images.stream().map(Image::getName).collect(Collectors.toList());
        EventResponseDto eventResponseDto = modelMapper.map(event, EventResponseDto.class);
        eventResponseDto.setImageName(imageName);
        return eventResponseDto;
    }

}
