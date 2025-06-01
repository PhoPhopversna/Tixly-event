package com.tixly.event_service.service;

import com.tixly.event_service.dto.OrganizerDto;

import java.util.List;

public interface OrganizerService {
    OrganizerDto createOrganizer(OrganizerDto organizerDto);
    OrganizerDto getOrganizerById(Integer id);
    List<OrganizerDto> getAllOrganizers();
    OrganizerDto updateOrganizer(Integer id, OrganizerDto organizerDto);
    void deleteOrganizer(Integer id);
}
