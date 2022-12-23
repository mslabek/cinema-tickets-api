package com.application.cinematicketsapi.screening.dto;

import com.application.cinematicketsapi.screening.model.Movie;

/**
 * Dto of {@link Movie} entity for communication with controllers
 *
 * @param id    the id of the movie
 * @param title the title of the movie
 */
public record MovieSimpleDto(Long id, String title) {
}
