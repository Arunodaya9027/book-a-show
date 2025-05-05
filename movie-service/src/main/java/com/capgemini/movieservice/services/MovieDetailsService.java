package com.capgemini.movieservice.services;

import com.capgemini.movieservice.dto.MovieInfoDto;
import com.capgemini.movieservice.dto.MovieShortInfoDto;
import com.capgemini.movieservice.entities.MovieInfo;
import com.capgemini.movieservice.interfaces.IMovieDetailsService;
import com.capgemini.movieservice.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDetailsService implements IMovieDetailsService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper = new ModelMapper(); // ModelMapper to map DTOs to entities and vice versa

    @Override
    public MovieInfoDto getMovieDetails(String movieName) {
        MovieInfo movieInfo = movieRepository.findByMovieName(movieName);
        return modelMapper.map(movieInfo, MovieInfoDto.class);
    }

    @Override
    public MovieInfoDto getMovieDetailsById(String movieId) {
        MovieInfo movieInfo = movieRepository.findById(Long.parseLong(movieId)).orElse(null);
        return modelMapper.map(movieInfo, MovieInfoDto.class);
    }

    @Override
    public MovieInfoDto getMovieDetailsByName(String movieName) {
        MovieInfo movieInfo = movieRepository.findByMovieName(movieName);
        return modelMapper.map(movieInfo, MovieInfoDto.class);
    }

    @Override
    public List<MovieShortInfoDto> getAllMovies() {
        List<MovieInfo> movieList = movieRepository.findAll();
        return movieList.stream()
                .map(movie -> modelMapper.map(movie, MovieShortInfoDto.class))
                .toList();
    }

    @Override
    public MovieInfoDto addMovie(MovieInfoDto movieInfoDto) {
        MovieInfo movieInfo = modelMapper.map(movieInfoDto, MovieInfo.class);
        movieRepository.save(movieInfo);
        return modelMapper.map(movieInfo, MovieInfoDto.class);
    }

    @Override
    public MovieInfoDto updateMovie(MovieInfoDto movieInfoDto) {
        MovieInfo movieInfo = modelMapper.map(movieInfoDto, MovieInfo.class);
        movieRepository.save(movieInfo);
        return modelMapper.map(movieInfo, MovieInfoDto.class);
    }

    @Override
    public String deleteMovie(String movieId) {
        movieRepository.deleteById(Long.parseLong(movieId));
        return "Movie deleted successfully";
    }
}
