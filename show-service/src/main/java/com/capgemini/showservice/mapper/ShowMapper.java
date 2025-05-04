package com.capgemini.showservice.mapper;


import com.capgemini.showservice.dto.ShowRequest;
import com.capgemini.showservice.dto.ShowResponse;
import com.capgemini.showservice.entities.Show;

public class ShowMapper {

    public static Show toEntity(ShowRequest request) {
        return new Show(
                null,
                request.getMovieId(),
                request.getTheatreId(),
                request.getScreenId(),
                request.getStartTime(),
                request.getEndTime(),
                request.getPrice(),
                request.getTotalSeats()
        );
    }

    public static ShowResponse toDto(Show show, String movieName) {
        ShowResponse response = new ShowResponse();
        response.setId(show.getId());
        response.setMovieId(show.getMovieId());
        response.setTheatreId(show.getTheatreId());
        response.setScreenId(show.getScreenId());
        response.setStartTime(show.getStartTime());
        response.setEndTime(show.getEndTime());
        response.setPrice(show.getPrice());
        response.setTotalSeats(show.getTotalSeats());
        response.setMovieName(movieName);
        return response;
    }
}
