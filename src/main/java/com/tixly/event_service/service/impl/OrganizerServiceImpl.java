package com.tixly.event_service.service.impl;

import com.tixly.event_service.dto.OrganizerDto;
import com.tixly.event_service.entity.Organizer;
import com.tixly.event_service.service.OrganizerService;
import com.tixly.event_service.exception.GeneralException;
import com.tixly.event_service.exception.ResourceNotFoundException;
import com.tixly.event_service.repository.OrganizerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrganizerServiceImpl implements OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public OrganizerDto createOrganizer(OrganizerDto organizerDto) {
        try {
            log.info("request Json:({})", organizerDto);
            Organizer organizer = organizerRepository.save(modelMapper.map(organizerDto, Organizer.class));
            organizer.setCreatedAt(LocalDateTime.now());
            return modelMapper.map(organizer, OrganizerDto.class);
        }
        catch (Exception e) {
            log.info("error:", e.getMessage());
            throw new GeneralException();
        }
    }

    @Override
    public OrganizerDto getOrganizerById(Integer id) {
       try {
           Organizer organizer = organizerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "Organizer"));
           return modelMapper.map(organizer, OrganizerDto.class);
       } catch (ResourceNotFoundException e){
           log.error("Failed to fetch Organizer with id {}: {}", id, e.getMessage());
           throw e;
       } catch (Exception e) {
           log.error("Error occurred: {}", e.getMessage());
           throw new GeneralException();
       }
    }

    @Override
    public List<OrganizerDto> getAllOrganizers() {
        try{
            List<Organizer> organizers = organizerRepository.findAll();
            return organizers.stream().map(organizer -> modelMapper.map(organizer, OrganizerDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error occurred: {}", e.getMessage());
            throw new GeneralException();
        }
    }

    @Override
    public OrganizerDto updateOrganizer(Integer id, OrganizerDto organizerDto) {
        try {
            log.info("request Json:({})", organizerDto);
            Organizer organizer = organizerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "Organizer"));
            organizer.setName(organizerDto.getName());
            organizer.setEmail(organizerDto.getEmail());
            organizer.setModifiedAt(LocalDateTime.now());
            Organizer modifyOrganizer = organizerRepository.save(organizer);
            log.info("update data:({})", modifyOrganizer);
            return modelMapper.map(modifyOrganizer, OrganizerDto.class);
        } catch (ResourceNotFoundException e) {
            log.error("Failed to fetch Organizer with id {}: {}", id, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error occurred: {}", e.getMessage());
            throw new GeneralException();
        }
    }

    @Override
    public void deleteOrganizer(Integer id) {
        try {
            Organizer organizer = organizerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "Organizer"));
            organizerRepository.delete(organizer);
            return;
        } catch (ResourceNotFoundException e) {
            log.error("Failed to fetch Organizer with id {}: {}", id, e.getMessage());
            throw e;
        } catch (Exception e){
            log.error("Error occurred: {}", e.getMessage());
            throw new GeneralException();
        }
    }
}
