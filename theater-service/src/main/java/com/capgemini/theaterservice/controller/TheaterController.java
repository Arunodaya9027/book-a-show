package com.capgemini.theaterservice.controller;

import java.util.List;

import com.capgemini.theaterservice.dto.request.TheaterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.theaterservice.dto.response.ResponseTheaterDto;
import com.capgemini.theaterservice.dto.response.ResponseTheaterListDto;
import com.capgemini.theaterservice.interfaces.ITheaterService;

@RestController
@RequestMapping("/theaters/v1/api")
public class TheaterController {
    @Autowired
    private ITheaterService theaterService;

    @GetMapping("/all")
    public ResponseEntity<ResponseTheaterListDto> getAllTheaters() {
    	try {
    		List<TheaterRequestDto> allTheaterDetailsDtos = theaterService.getAllTheaters();
    		ResponseTheaterListDto response = new ResponseTheaterListDto("All Theaters are Here", 200, allTheaterDetailsDtos);
    		return ResponseEntity.ok(response);
    	} catch(Exception e) {
    		ResponseTheaterListDto errorResponse = new ResponseTheaterListDto("No Theaters are registerd on the website", 500, null);
    		return ResponseEntity.status(500).body(errorResponse);
    	}
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTheaterDto> getTheaterById(@PathVariable Long id) {
    	try {
            TheaterRequestDto theaterDetailsDto = theaterService.getTheaterById(id);
    		ResponseTheaterDto response = new ResponseTheaterDto("Theater With id " + id + " are Here", 200, theaterDetailsDto);
    		return ResponseEntity.ok(response);
    	} catch(Exception e) {
    		ResponseTheaterDto errorResponse = new ResponseTheaterDto("Theater with id " + id + " is not found", 500, null);
    		return ResponseEntity.status(500).body(errorResponse);
    	}
    }
    
    @GetMapping("/search/name/{name}")
    public ResponseEntity<ResponseTheaterListDto> getTheaterByName(@PathVariable String name) {
        StringBuilder nameBuilder = new StringBuilder();
        String[] nameParts = name.split("-");
        for (int i = 0; i < nameParts.length; i++) {
            if (i == nameParts.length - 1) {
                nameBuilder.append(nameParts[i]);
            } else {
                nameBuilder.append(nameParts[i]).append(" ");
            }
        }
        String theaterName = nameBuilder.toString();
        try {
            List<TheaterRequestDto> theaters = theaterService.getTheaterByName(theaterName);
            ResponseTheaterListDto response = new ResponseTheaterListDto("Theaters with name " + theaterName + " are Here", 200, theaters);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseTheaterListDto errorResponse = new ResponseTheaterListDto("No Theaters found with name " + theaterName, 404, null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/search/location/{location}")
    public ResponseEntity<ResponseTheaterListDto> getTheaterByLocation(@PathVariable String location) {
        StringBuilder locationBuilder = new StringBuilder();
        String[] locationParts = location.split("-");
        for (int i = 0; i < locationParts.length; i++) {
            if (i == locationParts.length - 1) {
                locationBuilder.append(locationParts[i]);
            } else {
                locationBuilder.append(locationParts[i]).append(" ");
            }
        }
        String locationName = locationBuilder.toString();
        try {
            List<TheaterRequestDto> theaters = theaterService.getTheaterByLocation(locationName);
            ResponseTheaterListDto response = new ResponseTheaterListDto("Theaters in location " + locationName + " are Here", 200, theaters);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseTheaterListDto errorResponse = new ResponseTheaterListDto("No Theaters found in location " + locationName, 404, null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseTheaterDto> createTheater(@RequestBody TheaterRequestDto theater) {
        try {
            TheaterRequestDto savedTheater = theaterService.saveTheater(theater);
            ResponseTheaterDto response = new ResponseTheaterDto("Theater Created Successfully", 201, savedTheater);
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            ResponseTheaterDto errorResponse = new ResponseTheaterDto("Error Creating Theater\n" + e.getMessage(), 500, null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTheaterDto> updateTheater(@PathVariable Long id, @RequestBody TheaterRequestDto theaterDetailsDto) {
        try {
            TheaterRequestDto updatedTheater = theaterService.updateTheater(id, theaterDetailsDto);
            ResponseTheaterDto response = new ResponseTheaterDto("Theater With id " + id + " is Updated", 200, updatedTheater);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseTheaterDto errorResponse = new ResponseTheaterDto("Theater with id " + id + " is not found", 404, null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseTheaterDto> deleteTheater(@PathVariable Long id) {
        try {
            theaterService.deleteTheater(id);
            ResponseTheaterDto response = new ResponseTheaterDto("Theater Deleted Successfully", 200, null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseTheaterDto errorResponse = new ResponseTheaterDto("Error Deleting Theater", 500, null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}