package com.tixly.event_service.repository;

import com.tixly.event_service.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
