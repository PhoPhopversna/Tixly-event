package com.tixly.event_service.Service;

import com.tixly.event_service.Dto.EventRequestDto;
import com.tixly.event_service.Dto.EventResponseDto;
import com.tixly.event_service.Model.Event;

import java.util.List;

public interface EventService {
    EventResponseDto createEvent(EventRequestDto eventRequestDto);
    EventResponseDto getEventById(Integer id);
    List<EventResponseDto> getAllEvents();
    EventResponseDto updateEvent(Integer id, EventRequestDto eventRequestDto);
    void deleteEvent(Integer id);
}
