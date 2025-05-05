package com.capgemini.theaterservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.capgemini.theaterservice.dto.ScreenDto;
import com.capgemini.theaterservice.dto.request.TheaterRequestDto;
import com.capgemini.theaterservice.entities.Screen;
import com.capgemini.theaterservice.interfaces.IScreenService;
import com.capgemini.theaterservice.mapper.ScreenMapper;
import com.capgemini.theaterservice.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.theaterservice.entities.TheaterDetails;
import com.capgemini.theaterservice.exception.TheaterNotFoundException;
import com.capgemini.theaterservice.interfaces.ITheaterService;
import com.capgemini.theaterservice.mapper.TheaterMapper;
import com.capgemini.theaterservice.repository.TheaterRepository;

@Service
public class TheaterService implements ITheaterService {
	
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private IScreenService screenService;
    
    @Autowired
    private TheaterMapper theaterMapper;

    @Autowired
    private ScreenMapper screenMapper;

    @Override
    public List<TheaterRequestDto> getAllTheaters() {
    	List<TheaterDetails> theaterList = theaterRepository.findAll();
        return theaterList.stream()
                .map(theater -> theaterMapper.toDto(theater))
                .toList();
    }

    @Override
    public TheaterRequestDto getTheaterById(Long id) throws TheaterNotFoundException {
        Optional<TheaterDetails> theaterDetails = theaterRepository.findById(id);
        if(theaterDetails.isEmpty()) {
        	throw new TheaterNotFoundException("The Theater you are searching is not available");
        }
        return theaterMapper.toDto(theaterDetails.get());
    }
    
    @Override
    public List<TheaterRequestDto> getTheaterByLocation(String location) throws TheaterNotFoundException {
    	List<TheaterDetails> theaterDetails = theaterRepository.findByLocation(location.toLowerCase());
    	if(theaterDetails.isEmpty()) {
        	throw new TheaterNotFoundException("No Theater is registered on " + location + " region");
        }
    	return theaterDetails.stream()
                .map(theater -> theaterMapper.toDto(theater))
                .toList();
    }
    
    @Override
	public List<TheaterRequestDto> getTheaterByName(String theaterName) throws TheaterNotFoundException {
    	List<TheaterDetails> theaterDetails = theaterRepository.findByTheaterName(theaterName.toLowerCase());
    	if(theaterDetails.isEmpty()) {
        	throw new TheaterNotFoundException("No Theater is registered with name " + theaterName);
        }
    	return theaterDetails.stream()
                .map(theater -> theaterMapper.toDto(theater))
                .toList();
    }
    
    @Override
    public TheaterRequestDto updateTheater(Long id, TheaterRequestDto updatedTheaterDetailsDto) throws TheaterNotFoundException {
        Optional<TheaterDetails> optionalTheater = theaterRepository.findById(id);

        if (optionalTheater.isEmpty()) {
            throw new RuntimeException("Theater not found with ID: " + id);
        }

        // Update the existing theater details
        TheaterDetails existingTheater = optionalTheater.get();
        existingTheater.setName(updatedTheaterDetailsDto.getName());
        existingTheater.setLocation(updatedTheaterDetailsDto.getLocation());

        // Clear old screens and add new
        screenService.deleteScreensByTheaterId(id);

        List<Screen> newScreens = updatedTheaterDetailsDto.getScreens()
                                    .stream()
                                    .map(screenDto -> screenMapper.toEntity(existingTheater, screenDto))
                                    .toList();
        existingTheater.setScreens(newScreens);
        // Save the updated theater
        TheaterDetails updatedTheater = theaterRepository.save(existingTheater);
        return theaterMapper.toDto(updatedTheater);
    }

    @Override
    public TheaterRequestDto saveTheater(TheaterRequestDto theater) throws TheaterNotFoundException {
    	TheaterDetails theaterDetails = theaterMapper.toEntity(theater);
        List<Screen> screens = screenService.addScreensToTheater(theaterDetails);
    	TheaterDetails saveTheater = theaterRepository.save(theaterDetails);

        long id = saveTheater.getId();
        if(getTheaterById(id) == null)
        	throw new TheaterNotFoundException("The Theater is not registered properly");
        return theater;
    }

    @Override
    public void deleteTheater(Long id) {
        theaterRepository.deleteById(id);
    }
}
