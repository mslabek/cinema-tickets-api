package com.application.cinematicketsapi.demo.service;

import com.application.cinematicketsapi.demo.model.MovieDemoData;
import com.application.cinematicketsapi.screening.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieObjectMother {

    public List<Movie> generateMovies(List<MovieDemoData> moviesData) {
        return moviesData.stream().map(this::generateMovie).toList();
    }

    public Movie generateMovie(MovieDemoData movieData) {
        Movie movie = new Movie();
        movie.setTitle(movieData.getTitle());
        return movie;
    }

}
