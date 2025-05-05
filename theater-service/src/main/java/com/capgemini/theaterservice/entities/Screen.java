package com.capgemini.theaterservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "screen")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id")
    private Long id;

    @Column(name = "screen_number")
    private int screenNumber;

    @Column(name = "capacity")
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private TheaterDetails theater;
}
