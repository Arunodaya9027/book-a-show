package com.capgemini.movieservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieShortInfoDto {
    private String movieName;
    private String movieLanguage;
    private String movieDuration;
    private String movieRating;
    private String movieAgeRating;
}
