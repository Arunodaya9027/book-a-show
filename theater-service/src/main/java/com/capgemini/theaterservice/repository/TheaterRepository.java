package com.capgemini.theaterservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.theaterservice.entities.TheaterDetails;

public interface TheaterRepository extends JpaRepository<TheaterDetails, Long> {
	@Query(value = "SELECT * FROM theater_details t WHERE LOWER(t.theater_name) LIKE CONCAT('%', LOWER(:theaterName), '%')", nativeQuery = true)
	List<TheaterDetails> findByTheaterName(String theaterName);

    @Query(value = "SELECT * FROM theater_details t WHERE t.theater_id = :theaterId", nativeQuery = true)
    Optional<TheaterDetails> findById(Long theaterId);
    
    @Query(value = "SELECT * FROM theater_details t WHERE LOWER(t.location) LIKE CONCAT('%', LOWER(:location), '%')", nativeQuery = true)
	List<TheaterDetails> findByLocation(String location);
}
