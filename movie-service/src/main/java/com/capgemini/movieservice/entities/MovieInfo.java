package com.capgemini.movieservice.entities;

import com.capgemini.movieservice.dto.ReviewsDto;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.capgemini.movieservice.utils.JsonTimestampSerializer;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

@Entity
@Table(name = "movie_info")
public class MovieInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="movies_movie_id_seq")
    @SequenceGenerator(name ="movies_movie_id_seq", sequenceName="movies_movie_id_seq", allocationSize=1)
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
    private Duration movieDuration;

    @Column(name = "movie_release_date")
    private Date movieReleaseDate;

    @Column(name = "movie_rating")
    private String movieRating;

//    @Column(name = "movie_trailer")
//    private String movieTrailer;
//
//    @Column(name = "movie_poster")
//    private String moviePoster;

    @Column(name = "movie_cast")
    private List<String> movieCast;

    @Column(name = "movie_director")
    private String movieDirector;

    @Column(name = "movie_production_company")
    private String movieProductionCompany;

//    @Column(name = "movie_box_office_collection")
//    private String movieBoxOfficeCollection;

    @Column(name = "movie_reviews")
    private List<ReviewsDto> movieReviews;

    @Column(name = "movie_age_rating")
    private String movieAgeRating;

    @Column(name = "movie_country")
    private String movieCountry;

    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(name="movie_createdon")
    private Timestamp movieCreatedon;

    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(name="movie_updatedon")
    private Timestamp movieUpdatedon;
}
