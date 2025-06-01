package com.tixly.event_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EventResponseDto {
    private Integer id;
    private String title;
    private String description;
    private String category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private Boolean published;
    private LocalDateTime createdAt;
    private String organizerName;
    private List<String> imageName;
}
