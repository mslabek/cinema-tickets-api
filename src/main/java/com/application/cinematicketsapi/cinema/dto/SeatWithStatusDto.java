package com.application.cinematicketsapi.cinema.dto;

import com.application.cinematicketsapi.cinema.model.Seat;
import com.application.cinematicketsapi.screening.model.Screening;

/**
 * Dto of {@link Seat} entity for communication with controllers containing the {@link SeatStatus} for a particular
 * {@link Screening}.
 *
 * @param id     the id of the seat
 * @param row    the row of the seat
 * @param column the column of the seat
 * @param status the status of the seat for a specific {@code Screening}
 */
public record SeatWithStatusDto(Long id, Integer row, Integer column, SeatStatus status) {
}
