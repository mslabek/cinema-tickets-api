package com.application.cinematicketsapi.ticket.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity representing a reservation.
 * <p>
 * Reservation in this context is a processed request made by a client to keep a specific ticket / tickets for this
 * client to buy in the future. The personal details of the person making the reservation are stored in the
 * {@code firstName} and {@code lastName} fields. The date of creation of the reservation is needed to calculate the
 * expiration date.
 * <p>
 * Reservation should expire in a certain time after it is made. This change is signified by changing the
 * {@link ReservationStatus}.
 */
@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Ticket> tickets;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setReservation(this);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
        ticket.setReservation(null);
    }

}
