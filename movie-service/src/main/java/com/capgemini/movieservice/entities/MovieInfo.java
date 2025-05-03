package com.capgemini.movieservice.entities;

import com.capgemini.movieservice.dto.ReviewsDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "movie_info")
public class MovieInfo {
    @Id
    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "movie_description")
    private String movieDescription;

    @Column(name = "movie_language")
    private String movieLanguage;

    @Column(name = "movie_genre")
    private String movieGenre;

    @Column(name = "movie_duration")
    private String movieDuration;

    @Column(name = "movie_release_date")
    private String movieReleaseDate;

    @Column(name = "movie_rating")
    private String movieRating;

    @Column(name = "movie_trailer")
    private String movieTrailer;

    @Column(name = "movie_poster")
    private String moviePoster;

    @Column(name = "movie_cast")
    private List<String> movieCast;

    @Column(name = "movie_director")
    private String movieDirector;

    @Column(name = "movie_production_company")
    private String movieProductionCompany;

    @Column(name = "movie_box_office_collection")
    private String movieBoxOfficeCollection;

    @Column(name = "movie_reviews")
    private List<ReviewsDto> movieReviews;

    @Column(name = "movie_age_rating")
    private String movieAgeRating;

    @Column(name = "movie_country")
    private String movieCountry;
}
