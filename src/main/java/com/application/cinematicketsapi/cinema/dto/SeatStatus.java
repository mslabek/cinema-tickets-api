package com.application.cinematicketsapi.cinema.dto;

/**
 * Enum representing status of a seat in a particular screening
 */
public enum SeatStatus {
    /**
     * The seat is available for reservation
     */
    FREE,
    /**
     * The seat is unavailable for reservation (either the ticket was paid for or has not yet been paid for but the
     * reservation of it is still active.
     */
    TAKEN
}
