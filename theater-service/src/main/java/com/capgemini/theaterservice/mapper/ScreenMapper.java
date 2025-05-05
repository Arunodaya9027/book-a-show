package com.capgemini.theaterservice.mapper;

import com.capgemini.theaterservice.dto.ScreenDto;
import com.capgemini.theaterservice.dto.request.TheaterRequestDto;
import com.capgemini.theaterservice.entities.Screen;
import com.capgemini.theaterservice.entities.TheaterDetails;
import org.springframework.stereotype.Component;

@Component
public class ScreenMapper {
    public Screen toEntity(TheaterDetails theaterDetails, ScreenDto ScreenDto) {
        Screen screen = new Screen();
        screen.setScreenNumber(ScreenDto.getScreenNumber());
        screen.setCapacity(ScreenDto.getCapacity());
        screen.setTheater(theaterDetails);
        return screen;
    }
}
