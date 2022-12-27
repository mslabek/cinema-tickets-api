package com.application.cinematicketsapi.screening.service;

import com.application.cinematicketsapi.common.exception.ResourceNotFoundException;
import com.application.cinematicketsapi.screening.dto.MovieDetailedDto;
import com.application.cinematicketsapi.screening.dto.MovieSimpleDto;
import com.application.cinematicketsapi.screening.mapper.MovieMapper;
import com.application.cinematicketsapi.screening.model.Movie;
import com.application.cinematicketsapi.screening.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

/**
 * Service handling operations on {@link Movie} objects.
 */
@Service
@RequiredArgsConstructor
public class MovieService implements MovieDtoService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    /**
     * Stores the {@link Movie} in repository.
     *
     * @param movie the movie to be persisted
     */
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    /**
     * Retrieves all {@link Movie} entities from repository.
     *
     * @return the list of dtos representing all found {@code Movie} entities
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Retrieves a {@link Movie} entity with specified {@code id} from repository.
     *
     * @param id the id of the searched {@code Movie}
     * @return the found {@code Movie} entity
     */
    public Movie getMovieWithScreenings(Long id) {
        return movieRepository.findByIdWithScreenings(id)
                              .orElseThrow(getResourceNotFoundExceptionSupplier());
    }

    /**
     * Retrieves a {@link Movie} entity with specified {@code title} from repository.
     *
     * @param title the title of the searched {@code Movie}
     * @return the found {@code Movie} entity
     */
    public Movie getMovie(String title) {
        return movieRepository.findByTitle(title)
                              .orElseThrow(getResourceNotFoundExceptionSupplier());
    }

    @Override
    public List<MovieSimpleDto> getAllMovieSimpleDto() {
        return getAllMovies().stream()
                             .map(movieMapper::movieToMovieSimpleDto)
                             .toList();
    }

    @Override
    public MovieDetailedDto getMovieDetailedDto(Long id) {
        return movieMapper.movieToMovieBigDto(getMovieWithScreenings(id));
    }

    private Supplier<RuntimeException> getResourceNotFoundExceptionSupplier() {
        return () -> new ResourceNotFoundException("Movie not found in the database.");
    }

}
