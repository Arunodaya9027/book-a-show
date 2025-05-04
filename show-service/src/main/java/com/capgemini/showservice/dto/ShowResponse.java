package com.capgemini.showservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowResponse {
    private Long id;
    private Long movieId;
    private String movieName; // From MovieClient
    private Long theatreId;
    private Long screenId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double price;
    private int totalSeats;
}
