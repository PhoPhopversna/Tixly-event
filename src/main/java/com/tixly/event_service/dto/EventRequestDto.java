package com.tixly.event_service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EventRequestDto {
    private List<MultipartFile> files;
    private Integer id;
    private String title;
    private String description;
    private String category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private Boolean published;
    private LocalDateTime createdAt;
    private Integer organizerId;
}
