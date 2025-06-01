package com.tixly.event_service.controller;

import com.tixly.event_service.dto.EventRequestDto;
import com.tixly.event_service.dto.EventResponseDto;
import com.tixly.event_service.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDto> createEvent(@ModelAttribute EventRequestDto eventRequestDto) {
        log.info("Request Json: {}", eventRequestDto);
        EventResponseDto created = eventService.createEvent(eventRequestDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getById(@PathVariable Integer id) {
        EventResponseDto eventResponseDto = eventService.getEventById(id);
        return ResponseEntity.ok(eventResponseDto);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<EventResponseDto> update(@PathVariable Integer id, @RequestBody EventRequestDto eventRequestDto){
        EventResponseDto eventResponseDto = eventService.updateEvent(id, eventRequestDto);
        return ResponseEntity.ok(eventResponseDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
}
