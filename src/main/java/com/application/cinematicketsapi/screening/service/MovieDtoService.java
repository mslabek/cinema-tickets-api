package com.application.cinematicketsapi.screening.service;

import com.application.cinematicketsapi.screening.dto.MovieDetailedDto;
import com.application.cinematicketsapi.screening.dto.MovieSimpleDto;
import com.application.cinematicketsapi.screening.model.Movie;

import java.util.List;

/**
 * This interface exposes {@link MovieService} methods that return dtos.
 */
public interface MovieDtoService {

    /**
     * Retrieves all {@link Movie} entities from repository mapped to simple dtos.
     *
     * @return the list of simple dtos representing all found  {@code Movie} entities
     */
    List<MovieSimpleDto> getAllMovieSimpleDto();

    /**
     * Retrieves a {@link Movie} entity from repository mapped to a detailed dto.
     *
     * @param id the id of the searched movie
     * @return the dto representing the found {@code Movie} entity
     */
    MovieDetailedDto getMovieDetailedDto(Long id);

}
