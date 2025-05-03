package com.capgemini.movieservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieInfoDto {
    private String movieName;
    private String movieDescription;
    private String movieLanguage;
    private String movieGenre;
    private String movieDuration;
    private String movieReleaseDate;
    private String movieRating;
    private String movieTrailer;

    private String moviePoster;
    private List<String> movieCast;
    private String movieDirector;
    private String movieProductionCompany;
    private String movieBoxOfficeCollection;
    private List<ReviewsDto> movieReviews;
    private String movieAgeRating;
    private String movieCountry;
}
