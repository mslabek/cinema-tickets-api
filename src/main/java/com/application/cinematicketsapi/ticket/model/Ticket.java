package com.application.cinematicketsapi.ticket.model;

import com.application.cinematicketsapi.cinema.model.Seat;
import com.application.cinematicketsapi.screening.model.Screening;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing a ticket.
 * <p>
 * Price of the ticket (stored as {@link Money}) is set during the creation of the ticket - either at the moment of
 * {@link Reservation} or at the moment of buying a ticket. The price depends on the status of the client, not on the
 * movie.
 * <p>
 * Status of the ticket (represented by {@link TicketStatus}) depends on the way it was created and whether the
 * reservation has expired. Classifying reserved and expired tickets as tickets may be seen as inconsistent but the
 * specification ordered the ability to calculate the total amount to pay during the reservation. This can be
 * interpreted as reservation not of {@link Seat seats} but of tickets. Regardless, storing the information about
 * whether the ticket is valid in the ticket table seems simpler than other solutions.
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id")
    private Money price;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
