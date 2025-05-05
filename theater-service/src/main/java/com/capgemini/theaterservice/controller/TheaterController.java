package com.capgemini.theaterservice.controller;

import java.util.List;

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

import com.capgemini.theaterservice.dto.TheaterDetailsDto;
import com.capgemini.theaterservice.dto.response.ResponseTheaterDto;
import com.capgemini.theaterservice.dto.response.ResponseTheaterListDto;
import com.capgemini.theaterservice.interfaces.ITheaterService;

@RestController
@RequestMapping("/api/v1/theaters")
public class TheaterController {
    @Autowired
    private ITheaterService theaterService;

    @GetMapping
    public ResponseEntity<ResponseTheaterListDto> getAllTheaters() {
    	try {
    		List<TheaterDetailsDto> allTheaterDetailsDtos = theaterService.getAllTheaters();
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
    		TheaterDetailsDto theaterDetailsDto = theaterService.getTheaterById(id);
    		ResponseTheaterDto response = new ResponseTheaterDto("Theater With id " + id + " are Here", 200, theaterDetailsDto);
    		return ResponseEntity.ok(response);
    	} catch(Exception e) {
    		ResponseTheaterDto errorResponse = new ResponseTheaterDto("Theater with id " + id + " is not found", 500, null);
    		return ResponseEntity.status(500).body(errorResponse);
    	}
    }
    
    @GetMapping("/search/name/{name}")
    public ResponseEntity<ResponseTheaterListDto> getTheaterByName(@PathVariable String name) {
        try {
            List<TheaterDetailsDto> theaters = theaterService.getTheaterByName(name);
            ResponseTheaterListDto response = new ResponseTheaterListDto("Theaters with name " + name + " are Here", 200, theaters);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseTheaterListDto errorResponse = new ResponseTheaterListDto("No Theaters found with name " + name, 404, null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/search/location/{location}")
    public ResponseEntity<ResponseTheaterListDto> getTheaterByLocation(@PathVariable String location) {
        try {
            List<TheaterDetailsDto> theaters = theaterService.getTheaterByLocation(location);
            ResponseTheaterListDto response = new ResponseTheaterListDto("Theaters in location " + location + " are Here", 200, theaters);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseTheaterListDto errorResponse = new ResponseTheaterListDto("No Theaters found in location " + location, 404, null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseTheaterDto> createTheater(@RequestBody TheaterDetailsDto theater) {
        try {
            TheaterDetailsDto savedTheater = theaterService.saveTheater(theater);
            ResponseTheaterDto response = new ResponseTheaterDto("Theater Created Successfully", 201, savedTheater);
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            ResponseTheaterDto errorResponse = new ResponseTheaterDto("Error Creating Theater", 500, null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTheaterDto> updateTheater(@PathVariable Long id, @RequestBody TheaterDetailsDto theaterDetailsDto) {
        try {
            TheaterDetailsDto updatedTheater = theaterService.updateTheater(id, theaterDetailsDto);
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