package com.capgemini.theaterservice.repository;

import com.capgemini.theaterservice.entities.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    /**
     * Custom query to find screens by theater ID.
     *
     * @param theaterId the ID of the theater
     * @return a list of Screen entities associated with the specified theater ID
     */

    @Query(value = "SELECT * FROM Screen s WHERE s.theater_id = :theaterId", nativeQuery = true)
    List<Screen> findByTheaterId(Long theaterId);
}
