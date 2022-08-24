package com.ibrahim.springbootrediscache.service;

import com.ibrahim.springbootrediscache.model.Movie;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieDataService {

    @Cacheable(value = "movies")
    public List<Movie> getAllMovieList(){
        System.out.println("Getting data from inside getAllMovieList() Method");
        return Arrays.asList(
                new Movie("123", "KGF",4),
                new Movie("456", "KGF-2", 4),
                new Movie("789", "Ghost Rider", 5)
        );
    }

    @Cacheable(value = "movies:details", key = "#movieId")
    public Movie getMovieDetails(String movieId){
        System.out.println("Getting data from inside getMovieDetails(String movieId) Method");
        return new Movie(movieId, "YoYo-Hunky", 5);
    }
}
