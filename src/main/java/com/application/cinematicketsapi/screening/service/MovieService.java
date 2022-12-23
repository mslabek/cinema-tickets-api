package com.application.cinematicketsapi.screening.service;

import com.application.cinematicketsapi.screening.dto.MovieDetailedDto;
import com.application.cinematicketsapi.screening.dto.MovieSimpleDto;
import com.application.cinematicketsapi.screening.mapper.MovieMapper;
import com.application.cinematicketsapi.screening.model.Movie;
import com.application.cinematicketsapi.screening.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service handling operations on {@link Movie} objects.
 */
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieWithScreenings(Long id) {
        return movieRepository.findByIdWithScreenings(id).orElseThrow(() -> new RuntimeException("Movie not found in " +
                "the database."));
    }

    public Movie getMovie(String title) {
        return movieRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("Movie not found in the " +
                "database."));
    }

    public List<MovieSimpleDto> getAllMovieSimpleDto() {
        return getAllMovies().stream().map(movieMapper::movieToMovieSimpleDto).toList();
    }

    public MovieDetailedDto getMovieDetailedDto(Long id) {
        return movieMapper.movieToMovieBigDto(getMovieWithScreenings(id));
    }

}
