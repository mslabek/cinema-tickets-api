package com.application.cinematicketsapi.screening.dto;

import com.application.cinematicketsapi.screening.model.Movie;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Dto of {@link Movie} entity for communication with controllers
 */
@Getter
@RequiredArgsConstructor
public class MovieSimpleDto {

    @Schema(description = "Movie id", example = "1")
    private final Long id;

    @Schema(description = "Movie title", example = "Kot w butach: Ostatnie życzenie")
    private final String title;

}
