package com.capgemini.movieservice.interfaces;

import com.capgemini.movieservice.dto.ReviewsDto;

import java.util.List;

public interface IReviewsService {
    String addReview(String reviewDto);

    String updateReview(String reviewDto);

    String deleteReview(String reviewId);

    List<ReviewsDto> getReviewsByMovieName(String movieName);

    List<ReviewsDto> getReviewsByUsername(String username);
}
