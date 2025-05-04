package com.capgemini.movieservice.repository;

import com.capgemini.movieservice.entities.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieInfo, Long> {
    @Query(value = "SELECT * FROM MovieInfo m WHERE m.movieName = :movieName", nativeQuery = true)
    MovieInfo findByMovieName(String movieName);

    @Query(value = "SELECT * FROM MovieInfo m WHERE m.movieId = :movieId", nativeQuery = true)
    MovieInfo findByMovieId(Long movieId);
}
