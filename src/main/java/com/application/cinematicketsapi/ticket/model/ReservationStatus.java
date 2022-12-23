package com.application.cinematicketsapi.ticket.model;

/**
 * Enum representing the possible states of a {@link Reservation}
 */
public enum ReservationStatus {
    /**
     * The reservation has been made and the time to purchase the reserved tickets has not expired
     */
    ACTIVE,

    /**
     * The reserved {@link Ticket Tickets} have not been purchased in the allotted time.
     */
    EXPIRED
}
