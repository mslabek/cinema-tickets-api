package com.application.cinematicketsapi.cinema.dto;

import com.application.cinematicketsapi.cinema.model.Room;
import com.application.cinematicketsapi.cinema.model.Seat;

import java.util.List;

/**
 * Dto of {@link Room} entity for communication with controllers. This dto contains {@link SeatStatus} of all
 * {@link Seat Seats} belonging to it. This status is specific to a particular screening.
 *
 * @param id    the id of the room
 * @param seats the seats belonging to the room containing their {@link SeatStatus}
 */
public record RoomWithSeatStatusDto(Long id, List<SeatWithStatusDto> seats) {
}
