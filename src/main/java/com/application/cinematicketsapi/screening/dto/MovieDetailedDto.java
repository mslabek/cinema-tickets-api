package com.application.cinematicketsapi.screening.dto;

import com.application.cinematicketsapi.screening.model.Movie;

import java.util.List;

/**
 * Dto of {@link Movie} entity for communication with controllers
 *
 * @param id         the id of the movie
 * @param title      the title of the movie
 * @param screenings the list of screenings of the movie
 */
public record MovieDetailedDto(Long id, String title, List<ScreeningSimpleDto> screenings) {
}
