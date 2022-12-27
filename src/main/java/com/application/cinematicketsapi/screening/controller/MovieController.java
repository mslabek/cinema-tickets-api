package com.application.cinematicketsapi.screening.controller;

import com.application.cinematicketsapi.screening.dto.MovieDetailedDto;
import com.application.cinematicketsapi.screening.dto.MovieSimpleDto;
import com.application.cinematicketsapi.screening.model.Movie;
import com.application.cinematicketsapi.screening.service.MovieDtoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Movies", description = "Operations referring to movies")
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
    @Operation(summary = "Retrieves all movies with limited information")
    @ApiResponse(responseCode = "200", description = "Movies retrieved successfully")
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
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
    @Operation(summary = "Retrieves a movie with all its screenings")
    @Parameter(name = "id", description = "the id of the movie to be retrieved", example = "1")
    @ApiResponse(responseCode = "200", description = "Movie retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "404", description = "Movie with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public MovieDetailedDto getMovie(
            @PathVariable @Min(value = 1, message = "Movie id cannot be smaller than 1.") Long id) {
        return movieService.getMovieDetailedDto(id);
    }

}
