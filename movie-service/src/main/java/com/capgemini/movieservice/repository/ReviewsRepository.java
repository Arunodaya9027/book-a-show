package com.capgemini.movieservice.repository;

import com.capgemini.movieservice.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    // Custom query method to find reviews by movie name
    @Query(value = "SELECT * FROM Reviews r WHERE r.movieName = :movieName", nativeQuery = true)
    List<Reviews> findByMovieName(String movieName);

    // Custom query method to find reviews by username
    @Query(value = "SELECT * FROM Reviews r WHERE r.username = :username", nativeQuery = true)
    List<Reviews> findByUsername(String username);

    // Custom query method to find reviews by review ID
    @Query(value = "SELECT * FROM Reviews r WHERE r.reviewId = :reviewId", nativeQuery = true)
    Reviews findByReviewId(Long reviewId);

    // Custom query method to delete reviews by review ID
    @Query(value = "DELETE FROM Reviews r WHERE r.reviewId = :reviewId", nativeQuery = true)
    int deleteByReviewId(Long reviewId);

    // Custom query method to update reviews by review ID
    @Query(value = "UPDATE Reviews r SET r.review = :review WHERE r.reviewId = :reviewId", nativeQuery = true)
    int updateByReviewId(Long reviewId, String review);

    // Custom query method to find reviews by movie ID
    @Query(value = "SELECT * FROM Reviews r WHERE r.movieId = :movieId", nativeQuery = true)
    List<Reviews> findByMovieId(Long movieId);
}
