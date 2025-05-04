package com.capgemini.showservice.service.impl;

import com.capgemini.showservice.dto.ShowRequest;
import com.capgemini.showservice.dto.ShowResponse;
import com.capgemini.showservice.entities.Show;
import com.capgemini.showservice.exception.ResourceNotFoundException;
import com.capgemini.showservice.feign.MovieClient;
import com.capgemini.showservice.mapper.ShowMapper;
import com.capgemini.showservice.repository.ShowRepository;
import com.capgemini.showservice.service.IShowService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements IShowService {

    private final ShowRepository showRepository;
    private final MovieClient movieClient;

    // Method with Circuit Breaker and fallback
    @CircuitBreaker(name = "movieService", fallbackMethod = "getMovieNameFallback")
    @Override
    public ShowResponse createShow(ShowRequest req) {
        Show show = ShowMapper.toEntity(req);
        show = showRepository.save(show);

        // Movie name call, handled by Feign
        String movieName = movieClient.getMovieName(show.getMovieId());

        return ShowMapper.toDto(show, movieName);
    }

    // Fallback method for createShow
    public ShowResponse getMovieNameFallback(ShowRequest req, Throwable t) {
        // Provide default movie name when circuit is open or call fails
        Show show = ShowMapper.toEntity(req);
        show = showRepository.save(show);

        return ShowMapper.toDto(show, "Default Movie Name");
    }

    @Override
    public List<ShowResponse> getShowsByMovieId(Long movieId) {
        List<Show> shows = showRepository.findByMovieId(movieId);

        if (shows.isEmpty()) {
            throw new ResourceNotFoundException("No shows found for movie ID: " + movieId);
        }

        String movieName = movieClient.getMovieName(movieId);

        return shows.stream()
                .map(show -> ShowMapper.toDto(show, movieName))
                .collect(Collectors.toList());
    }

    @Override
    public ShowResponse getShowById(Long id) {
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Show not found with ID: " + id));

        String movieName = movieClient.getMovieName(show.getMovieId());

        return ShowMapper.toDto(show, movieName);
    }

    @Override
    public void deleteShow(Long id) {
        if (!showRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Show not found with ID: " + id);
        }
        showRepository.deleteById(id);
    }
}
