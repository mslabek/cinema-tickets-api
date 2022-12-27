package com.application.cinematicketsapi.screening.dto;

import com.application.cinematicketsapi.cinema.dto.RoomWithSeatStatusDto;

import java.time.LocalDateTime;

public record ScreeningFullDto(Long id, LocalDateTime beginning, LocalDateTime ending, String title,
                               RoomWithSeatStatusDto room) {
}
