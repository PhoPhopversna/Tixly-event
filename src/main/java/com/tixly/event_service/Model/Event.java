package com.tixly.event_service.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private String category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private Boolean published;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private Organizer organizer;
    @OneToMany(mappedBy = "event" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> image;
}
