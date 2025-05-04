package com.capgemini.movieservice.interfaces;

import com.capgemini.movieservice.dto.MovieInfoDto;

import java.util.List;

public interface IMovieDetailsService {
    MovieInfoDto getMovieDetails(String movieName);

    MovieInfoDto getMovieDetailsById(String movieId);

    MovieInfoDto getMovieDetailsByName(String movieName);

    List<MovieShortInfoDto> getAllMovies();

    MovieInfoDto addMovie(MovieInfoDto movieInfoDto);

    MovieInfoDto updateMovie(MovieInfoDto movieInfoDto);

    String deleteMovie(String movieId);
}
