package com.capgemini.theaterservice.interfaces;

import java.util.List;

import com.capgemini.theaterservice.dto.TheaterDetailsDto;
import com.capgemini.theaterservice.exception.TheaterNotFoundException;

public interface ITheaterService {
    public List<TheaterDetailsDto> getAllTheaters();

    public TheaterDetailsDto getTheaterById(Long id) throws TheaterNotFoundException;
    
    public List<TheaterDetailsDto> getTheaterByLocation(String location) throws TheaterNotFoundException;
    
    public List<TheaterDetailsDto> getTheaterByName(String theaterName) throws TheaterNotFoundException;
    
    public TheaterDetailsDto saveTheater(TheaterDetailsDto theater) throws TheaterNotFoundException;
    
    public TheaterDetailsDto updateTheater(Long id, TheaterDetailsDto theaterDetailsDto) throws TheaterNotFoundException;

    public void deleteTheater(Long id);
}
