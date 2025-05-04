package com.capgemini.showservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "show_table")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long movieId;
    private Long theatreId;
    private Long screenId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double price;
    private int totalSeats;
}
