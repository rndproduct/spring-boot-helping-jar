package com.ibrahim.springbootrediscache.controller;

import com.ibrahim.springbootrediscache.model.Movie;
import com.ibrahim.springbootrediscache.service.MovieDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    private final MovieDataService movieDataService;

    public MovieController(MovieDataService movieDataService) {
        this.movieDataService = movieDataService;
    }

    @GetMapping
    public List<Movie> getMovieList(){
        return movieDataService.getAllMovieList();
    }

    @GetMapping("/{movieId}")
    public Movie getMovieDetails(@PathVariable("movieId") String movieId){
        return movieDataService.getMovieDetails(movieId);
    }
}
