package com.capgemini.movieservice.services;

import com.capgemini.movieservice.dto.ReviewsDto;
import com.capgemini.movieservice.entities.Reviews;
import com.capgemini.movieservice.interfaces.IReviewsService;
import com.capgemini.movieservice.repository.ReviewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService implements IReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    ModelMapper modelMapper = new ModelMapper();    // ModelMapper to map DTOs to entities and vice versa

    @Override
    public List<ReviewsDto> getReviewsByMovieName(String movieName) {
        List<Reviews> reviewsList = reviewsRepository.findByMovieName(movieName);
        return reviewsList.stream()
                .map(review -> modelMapper.map(review, ReviewsDto.class))
                .toList();
    }

    @Override
    public List<ReviewsDto> getReviewsByUsername(String username) {
        List<Reviews> reviewsList = reviewsRepository.findByUsername(username);
        return reviewsList.stream()
                .map(review -> modelMapper.map(review, ReviewsDto.class))
                .toList();
    }

    @Override
    public String addReview(String reviewDto) {
        Reviews review = modelMapper.map(reviewDto, Reviews.class);
        reviewsRepository.save(review);
        return "Review added successfully";
    }

    @Override
    public String updateReview(String reviewDto) {
        Reviews review = modelMapper.map(reviewDto, Reviews.class);
        reviewsRepository.save(review);
        return "Review updated successfully";
    }

    @Override
    public String deleteReview(String reviewId) {
        reviewsRepository.deleteByReviewId(Long.parseLong(reviewId));
        return "Review deleted successfully";
    }
}
