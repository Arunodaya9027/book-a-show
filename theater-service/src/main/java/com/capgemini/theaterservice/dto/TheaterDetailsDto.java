package com.capgemini.theaterservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDetailsDto {
	private String name;
    private String location;
    private int capacity;
}
