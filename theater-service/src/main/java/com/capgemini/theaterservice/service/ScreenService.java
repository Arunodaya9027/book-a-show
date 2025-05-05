package com.capgemini.theaterservice.service;

import com.capgemini.theaterservice.dto.ScreenDto;
import com.capgemini.theaterservice.entities.Screen;
import com.capgemini.theaterservice.entities.TheaterDetails;
import com.capgemini.theaterservice.exception.ScreenNotFoundException;
import com.capgemini.theaterservice.repository.ScreenRepository;
import com.capgemini.theaterservice.interfaces.IScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScreenService implements IScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Override
    public List<Screen> getScreensByTheaterId(Long theaterId) {
        List<Screen> screens = screenRepository.findByTheaterId(theaterId);
        if (screens.isEmpty()) {
            throw new ScreenNotFoundException("No screens found for theater ID: " + theaterId);
        }
        return screens;
    }

    @Override
    public List<Screen> addScreensToTheater(TheaterDetails theater) {
        return theater.getScreens();
    }

    @Override
    public void deleteScreensByTheaterId(Long theaterId) {
        List<Screen> screens = screenRepository.findByTheaterId(theaterId);
        screenRepository.deleteAll(screens);
    }

    @Override
    public void saveAll(List<Screen> screens) throws ScreenNotFoundException {
        List<Screen> savedScreens = screenRepository.saveAll(screens);
        if (savedScreens.isEmpty()) {
            throw new ScreenNotFoundException("No screens were saved.");
        }
    }
}
