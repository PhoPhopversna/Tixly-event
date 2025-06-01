package com.tixly.event_service.controller;

import com.tixly.event_service.Dto.OrganizerDto;
import com.tixly.event_service.Service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/organizer")
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;
    @PostMapping
    public ResponseEntity<OrganizerDto> create(@RequestBody OrganizerDto organizer) {
        return ResponseEntity.ok(organizerService.createOrganizer(organizer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizerDto> update(@PathVariable Integer id, @RequestBody OrganizerDto organizer) {
        return ResponseEntity.ok(organizerService.updateOrganizer(id, organizer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        organizerService.deleteOrganizer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizerDto> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(organizerService.getOrganizerById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrganizerDto>> getAll() {
        return ResponseEntity.ok(organizerService.getAllOrganizers());
    }
}
