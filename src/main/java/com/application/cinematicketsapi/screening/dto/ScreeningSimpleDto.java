package com.application.cinematicketsapi.screening.dto;

import com.application.cinematicketsapi.screening.model.Screening;

import java.time.LocalDateTime;

/**
 * Dto of a {@link Screening} for communication with controllers.
 *
 * @param id        the id of the screening
 * @param roomId    the id of the room where the screening takes place
 * @param beginning the time when the screening starts
 * @param ending    the time when the screening ends
 */
public record ScreeningSimpleDto(Long id, Long roomId, LocalDateTime beginning, LocalDateTime ending) {
}
