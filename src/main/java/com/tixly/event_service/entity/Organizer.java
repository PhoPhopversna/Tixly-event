package com.tixly.event_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime modifiedAt;
    @OneToMany(mappedBy = "organizer")
    private List<Event> events = new ArrayList<>();
}
