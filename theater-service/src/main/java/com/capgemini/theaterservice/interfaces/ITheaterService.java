package com.capgemini.theaterservice.interfaces;

import java.util.List;

//import com.capgemini.theaterservice.dto.request.TheaterDetailsDto;
import com.capgemini.theaterservice.dto.request.TheaterRequestDto;
import com.capgemini.theaterservice.exception.TheaterNotFoundException;

public interface ITheaterService {
    public List<TheaterRequestDto> getAllTheaters();

    public TheaterRequestDto getTheaterById(Long id) throws TheaterNotFoundException;
    
    public List<TheaterRequestDto> getTheaterByLocation(String location) throws TheaterNotFoundException;
    
    public List<TheaterRequestDto> getTheaterByName(String theaterName) throws TheaterNotFoundException;
    
    public TheaterRequestDto saveTheater(TheaterRequestDto theater) throws TheaterNotFoundException;
    
    public TheaterRequestDto updateTheater(Long id, TheaterRequestDto theaterDetailsDto) throws TheaterNotFoundException;

    public void deleteTheater(Long id);
}
