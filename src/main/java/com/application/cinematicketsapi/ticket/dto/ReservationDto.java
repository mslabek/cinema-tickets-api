package com.application.cinematicketsapi.ticket.dto;

import com.application.cinematicketsapi.ticket.model.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationDto(Long id, String name, String surname, ReservationStatus status,
                             LocalDateTime createdAt, LocalDateTime expiresAt, MoneyDto totalAmountToPay,
                             List<TicketDto> tickets) {
}
