package com.application.cinematicketsapi.ticket.dto;

import com.application.cinematicketsapi.ticket.model.TicketStatus;
import com.application.cinematicketsapi.ticket.model.TicketType;

public record TicketDto(Long id, TicketStatus status, Integer roomId, Integer row, Integer column, TicketType type,
                        MoneyDto price) {
}
