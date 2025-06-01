package com.tixly.event_service.Model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
