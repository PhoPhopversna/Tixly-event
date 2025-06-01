package com.tixly.event_service.service;

import com.tixly.event_service.dto.EventRequestDto;
import com.tixly.event_service.dto.EventResponseDto;

import java.util.List;

public interface EventService {
    EventResponseDto createEvent(EventRequestDto eventRequestDto);
    EventResponseDto getEventById(Integer id);
    List<EventResponseDto> getAllEvents();
    EventResponseDto updateEvent(Integer id, EventRequestDto eventRequestDto);
    void deleteEvent(Integer id);
}
