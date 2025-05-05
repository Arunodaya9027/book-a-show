package com.capgemini.theaterservice.mapper;

import com.capgemini.theaterservice.dto.ScreenDto;
import com.capgemini.theaterservice.dto.request.TheaterRequestDto;
import com.capgemini.theaterservice.entities.Screen;
import org.springframework.stereotype.Component;

import com.capgemini.theaterservice.entities.TheaterDetails;

import java.util.ArrayList;
import java.util.List;

@Component
public class TheaterMapper {

	public TheaterDetails toEntity(TheaterRequestDto theaterDetailsDto) {
		TheaterDetails theaterDetails = new TheaterDetails();
		theaterDetails.setName(theaterDetailsDto.getName());
		theaterDetails.setLocation(theaterDetailsDto.getLocation());

		List<Screen> screens = new ArrayList<>();
		for (ScreenDto screenDTO : theaterDetailsDto.getScreens()) {
			Screen screen = new Screen();
			screen.setScreenNumber(screenDTO.getScreenNumber());
			screen.setCapacity(screenDTO.getCapacity());
			screen.setTheater(theaterDetails); // Set the relation
			screens.add(screen);
		}

		theaterDetails.setScreens(screens);
		return theaterDetails;
	}
	
	public TheaterRequestDto toDto(TheaterDetails theaterDetails) {
		TheaterRequestDto theaterDetailsDto = new TheaterRequestDto();
		theaterDetailsDto.setName(theaterDetails.getName());
		theaterDetailsDto.setLocation(theaterDetails.getLocation());

		List<ScreenDto> screens = new ArrayList<>();
		for (Screen screen : theaterDetails.getScreens()) {
			ScreenDto screenDTO = new ScreenDto();
			screenDTO.setScreenNumber(screen.getScreenNumber());
			screenDTO.setCapacity(screen.getCapacity());
			screens.add(screenDTO);
		}

		theaterDetailsDto.setScreens(screens);
		return theaterDetailsDto;
	}
}
