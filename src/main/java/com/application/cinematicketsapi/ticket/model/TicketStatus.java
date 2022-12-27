package com.application.cinematicketsapi.ticket.model;

/**
 * Enum representing the possible states of a {@link Ticket}.
 */
public enum TicketStatus {
    /**
     * The ticket can only be sold to a person that made the {@link Reservation}.
     */
    RESERVED,

    /**
     * The ticket has been paid for.
     */
    SOLD,

    /**
     * The ticket had been reserved, but was not purchased in the allotted time. Other tickets can be issued to the seat
     * the expired ticket was for.
     */
    EXPIRED
}
