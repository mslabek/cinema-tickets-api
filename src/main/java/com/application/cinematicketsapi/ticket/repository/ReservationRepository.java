package com.application.cinematicketsapi.ticket.repository;

import com.application.cinematicketsapi.ticket.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to perform CRUD operations on {@link Reservation} entities in the persistence layer.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
