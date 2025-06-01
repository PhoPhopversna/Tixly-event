package com.tixly.event_service.repository;

import com.tixly.event_service.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
