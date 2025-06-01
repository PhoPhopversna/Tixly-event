package com.tixly.event_service.repository;

import com.tixly.event_service.Model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
}
