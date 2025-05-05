package com.capgemini.theaterservice.mapper;

import org.springframework.stereotype.Component;

import com.capgemini.theaterservice.dto.TheaterDetailsDto;
import com.capgemini.theaterservice.entities.TheaterDetails;

@Component
public class Mapper {

	public TheaterDetails toEntity(TheaterDetailsDto theaterDetailsDto) {
		TheaterDetails theaterDetails = new TheaterDetails();
		theaterDetails.setName(theaterDetailsDto.getName());
		theaterDetails.setLocation(theaterDetailsDto.getLocation());
		theaterDetails.setCapacity(theaterDetailsDto.getCapacity());
		
		return theaterDetails;
	}
	
	public TheaterDetailsDto toDto(TheaterDetails theaterDetails) {
		TheaterDetailsDto theaterDetailsDto = new TheaterDetailsDto();
		theaterDetailsDto.setName(theaterDetails.getName());
		theaterDetailsDto.setLocation(theaterDetails.getLocation());
		theaterDetailsDto.setCapacity(theaterDetails.getCapacity());
		
		return theaterDetailsDto;
	}
}
