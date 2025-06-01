package com.tixly.event_service.Dto;

import com.tixly.event_service.Model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrganizerDto {
    private Integer id;
    private String name;
    private String email;
}
