package com.capgemini.movieservice.controller;


import com.capgemini.movieservice.dto.MovieInfoDto;
import com.capgemini.movieservice.dto.Response.ResponseDto;
import com.capgemini.movieservice.interfaces.IMovieDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/v1/movies")
public class MovieController {

    @Autowired
    private IMovieDetailsService movieDetailsService;

    @GetMapping("/getAllMovies")
    public ResponseEntity<ResponseDto<?>> getAllMovies() {
        try {
            List<?> movieList = movieDetailsService.getAllMovies();
            ResponseDto<List<?>> response = new ResponseDto<>(200, "Success", movieList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseDto<String> errorResponse = new ResponseDto<>(500, "Internal Server Error", null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/getMovieDetails")
    public ResponseEntity<ResponseDto<?>> getMovieDetails(RequestEntity<String> request) {
        String movieName = request.getBody();
        try {
            Object movieDetails = movieDetailsService.getMovieDetails(movieName);
            ResponseDto<Object> response = new ResponseDto<>(200, "Success", movieDetails);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseDto<String> errorResponse = new ResponseDto<>(500, "Internal Server Error", null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/getMovieDetailsById")
    public ResponseEntity<ResponseDto<?>> getMovieDetailsById(RequestEntity<String> request) {
        String movieId = request.getBody();
        try {
            Object movieDetails = movieDetailsService.getMovieDetailsById(movieId);
            ResponseDto<Object> response = new ResponseDto<>(200, "Success", movieDetails);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseDto<String> errorResponse = new ResponseDto<>(500, "Internal Server Error", null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/getMovieDetailsByName")
    public ResponseEntity<ResponseDto<?>> getMovieDetailsByName(RequestEntity<String> request) {
        String movieName = request.getBody();
        try {
            Object movieDetails = movieDetailsService.getMovieDetailsByName(movieName);
            ResponseDto<Object> response = new ResponseDto<>(200, "Success", movieDetails);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseDto<String> errorResponse = new ResponseDto<>(500, "Internal Server Error", null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping("/addMovie")
    public ResponseEntity<ResponseDto<?>> addMovie(@RequestBody MovieInfoDto movieInfoDto) {
        try {
            Object addedMovie = movieDetailsService.addMovie(movieInfoDto);
            ResponseDto<Object> response = new ResponseDto<>(201, "Movie added successfully", addedMovie);
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            ResponseDto<String> errorResponse = new ResponseDto<>(500, "Internal Server Error", null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PutMapping("/updateMovie")
    public ResponseEntity<ResponseDto<?>> updateMovie(@RequestBody MovieInfoDto movieInfoDto) {
        try {
            Object updatedMovie = movieDetailsService.updateMovie(movieInfoDto);
            ResponseDto<Object> response = new ResponseDto<>(200, "Movie updated successfully", updatedMovie);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseDto<String> errorResponse = new ResponseDto<>(500, "Internal Server Error", null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @DeleteMapping("/deleteMovie")
    public ResponseEntity<ResponseDto<?>> deleteMovie(RequestEntity<String> request) {
        String movieId = request.getBody();
        try {
            String result = movieDetailsService.deleteMovie(movieId);
            ResponseDto<String> response = new ResponseDto<>(200, "Movie deleted successfully", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseDto<String> errorResponse = new ResponseDto<>(500, "Internal Server Error", null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
