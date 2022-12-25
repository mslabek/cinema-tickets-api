package com.application.cinematicketsapi.cinema.dto;

import java.util.List;

public record RoomWithSeatStatusDto(Long id, List<SeatWithStatusDto> seats) {
}
