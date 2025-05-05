package com.capgemini.theaterservice.interfaces;

import com.capgemini.theaterservice.dto.ScreenDto;
import com.capgemini.theaterservice.entities.Screen;
import com.capgemini.theaterservice.entities.TheaterDetails;
import com.capgemini.theaterservice.exception.ScreenNotFoundException;

import java.util.List;

public interface IScreenService {
    List<Screen> getScreensByTheaterId(Long theaterId);
    List<Screen> addScreensToTheater(TheaterDetails theater);
    void deleteScreensByTheaterId(Long theaterId);
    void saveAll(List<Screen> screens) throws ScreenNotFoundException;
}
