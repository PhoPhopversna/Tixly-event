package com.tixly.event_service.Service;

import com.tixly.event_service.Dto.OrganizerDto;

import java.util.List;

public interface OrganizerService {
    OrganizerDto createOrganizer(OrganizerDto organizerDto);
    OrganizerDto getOrganizerById(Integer id);
    List<OrganizerDto> getAllOrganizers();
    OrganizerDto updateOrganizer(Integer id, OrganizerDto organizerDto);
    void deleteOrganizer(Integer id);
}
