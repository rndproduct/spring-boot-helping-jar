package com.ibrahim.springbootrediscache.model;

import java.io.Serializable;

public class Movie implements Serializable {
    private static final long serialVersionUUID = -1L;

    private String movieId;
    private String movieName;
    private int movieRating;

    public Movie() {
    }

    public Movie(String movieId, String movieName, int movieRating) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieRating = movieRating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", movieName='" + movieName + '\'' +
                ", movieRating=" + movieRating +
                '}';
    }
}
