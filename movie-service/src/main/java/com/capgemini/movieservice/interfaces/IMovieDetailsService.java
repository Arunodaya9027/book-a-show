package com.capgemini.movieservice.interfaces;

import com.capgemini.movieservice.dto.MovieInfoDto;

import java.util.List;

public interface IMovieDetailsService {
    MovieInfoDto getMovieDetails(String movieName);

    MovieInfoDto getMovieDetailsById(String movieId);

    MovieInfoDto getMovieDetailsByName(String movieName);

    List<MoviesShortInfoDto> getAllMovies();

    MovieInfoDto addMovie(String movieInfoDto);

    MovieInfoDto updateMovie(String movieInfoDto);

    String deleteMovie(String movieId);
}
