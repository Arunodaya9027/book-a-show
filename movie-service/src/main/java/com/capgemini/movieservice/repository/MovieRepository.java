package com.capgemini.movieservice.repository;

import com.capgemini.movieservice.entities.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieInfo, Long> {

}
