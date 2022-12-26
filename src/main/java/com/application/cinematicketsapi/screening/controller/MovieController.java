package com.application.cinematicketsapi.screening.controller;

import com.application.cinematicketsapi.screening.dto.MovieDetailedDto;
import com.application.cinematicketsapi.screening.dto.MovieSimpleDto;
import com.application.cinematicketsapi.screening.model.Movie;
import com.application.cinematicketsapi.screening.service.MovieDtoService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * {@code Rest controller} handling requests referring to {@link Movie movies}.
 */
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Validated
public class MovieController {

    private final MovieDtoService movieService;

    /**
     * Retrieves all {@link Movie Movies} from repository.
     *
     * @return the list of dtos representing the {@code Movie} entities
     */
    @GetMapping
    public List<MovieSimpleDto> getAllMovies() {
        return movieService.getAllMovieSimpleDto();
    }

    /**
     * Retrieves a {@link Movie} with specified {@code id} from repository.
     *
     * @param id the id of the specified movie
     * @return the dto representing the found {@code Movie} entity
     */
    @GetMapping("/{id}")
    public MovieDetailedDto getMovie(
            @PathVariable @Min(value = 1, message = "Movie id cannot be smaller than 1.") Long id) {
        return movieService.getMovieDetailedDto(id);
    }

}
