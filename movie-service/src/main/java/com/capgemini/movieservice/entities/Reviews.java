package com.capgemini.movieservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie_reviews")
public class Reviews {
    @Id
    @Column(name = "review_id")
    private long reviewId;

    @Column(name = "movie_id")
    private String username;

    @Column(name = "review")
    private String review;

    @Column(name = "review_date")
    private String reviewDate;

    @Column(name = "review_likes")
    private String reviewLikes;

//    @Column(name = "review_comment_date")
//    private String reviewCommentDate;
//
//    @Column(name = "review_comment_id")
//    private String reviewCommentId;
//
//    @Column(name = "review_comment_status")
//    private String reviewCommentStatus;
//
//    @Column(name = "review_comment_username")
//    private String reviewCommentUsername;
//
//    @Column(name = "review_comment_review_id")
//    private String reviewCommentReviewId;
}
