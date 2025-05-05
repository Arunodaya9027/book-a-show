package com.capgemini.theaterservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.theaterservice.dto.TheaterDetailsDto;
import com.capgemini.theaterservice.entities.TheaterDetails;
import com.capgemini.theaterservice.exception.TheaterNotFoundException;
import com.capgemini.theaterservice.interfaces.ITheaterService;
import com.capgemini.theaterservice.mapper.Mapper;
import com.capgemini.theaterservice.repository.TheaterRepository;

@Service
public class TheaterService implements ITheaterService {
	
    @Autowired
    private TheaterRepository theaterRepository;
    
    @Autowired
    private Mapper mapper;

    @Override
    public List<TheaterDetailsDto> getAllTheaters() {
    	List<TheaterDetails> theaterList = theaterRepository.findAll();
        return theaterList.stream()
                .map(theater -> mapper.toDto(theater))
                .toList();
    }

    @Override
    public TheaterDetailsDto getTheaterById(Long id) throws TheaterNotFoundException {
        Optional<TheaterDetails> theaterDetails = Optional.of(theaterRepository.findById(id).orElse(null));
        if(theaterDetails.get() == null) {
        	throw new TheaterNotFoundException("The Theater you are searching is not available");
        }
        return mapper.toDto(theaterDetails.get());
    }
    
    @Override
    public List<TheaterDetailsDto> getTheaterByLocation(String location) throws TheaterNotFoundException {
    	List<TheaterDetails> theaterDetails = theaterRepository.findByLocation(location);
    	if(theaterDetails.isEmpty()) {
        	throw new TheaterNotFoundException("No Theater is registered on " + location + " region");
        }
    	return theaterDetails.stream()
                .map(theater -> mapper.toDto(theater))
                .toList();
    }
    
    @Override
	public List<TheaterDetailsDto> getTheaterByName(String theaterName) throws TheaterNotFoundException {
    	List<TheaterDetails> theaterDetails = theaterRepository.findByTheaterName(theaterName);
    	if(theaterDetails.isEmpty()) {
        	throw new TheaterNotFoundException("No Theater is registered with name " + theaterName);
        }
    	return theaterDetails.stream()
                .map(theater -> mapper.toDto(theater))
                .toList();
    }
    
    @Override
    public TheaterDetailsDto updateTheater(Long id, TheaterDetailsDto theaterDetailsDto) throws TheaterNotFoundException {
        TheaterDetails existingTheater = theaterRepository.findById(id).orElseThrow(() -> new TheaterNotFoundException("Theater not found"));
        existingTheater.setName(theaterDetailsDto.getName());
        existingTheater.setLocation(theaterDetailsDto.getLocation());
        existingTheater.setCapacity(theaterDetailsDto.getCapacity());
        TheaterDetails updatedTheater = theaterRepository.save(existingTheater);
        return mapper.toDto(updatedTheater);
    }

    @Override
    public TheaterDetailsDto saveTheater(TheaterDetailsDto theater) throws TheaterNotFoundException {
    	TheaterDetails theaterDetails = mapper.toEntity(theater);
    	TheaterDetails saveTheater = theaterRepository.save(theaterDetails);
    	long id = saveTheater.getId();
        
        if(getTheaterById(id) == null)
        	throw new TheaterNotFoundException("The Theater you are searching is not available");
        return theater;
    }

    @Override
    public void deleteTheater(Long id) {
        theaterRepository.deleteById(id);
    }
}
