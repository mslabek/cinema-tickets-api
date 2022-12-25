package com.application.cinematicketsapi.cinema.dto;

public record SeatWithStatusDto(Long id, Integer row, Integer column, SeatStatus status) {
}
