package com.capgemini.showservice.service;

import com.capgemini.showservice.dto.ShowRequest;
import com.capgemini.showservice.dto.ShowResponse;

import java.util.List;

public interface IShowService {
    ShowResponse createShow(ShowRequest request);
    List<ShowResponse> getShowsByMovieId(Long movieId);
    ShowResponse getShowById(Long id);
    void deleteShow(Long id);
}
