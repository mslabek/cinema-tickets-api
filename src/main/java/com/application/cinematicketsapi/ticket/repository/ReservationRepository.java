package com.application.cinematicketsapi.ticket.repository;

import com.application.cinematicketsapi.ticket.model.Reservation;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository used to perform CRUD operations on {@link Reservation} entities in the persistence layer.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Reservation r " +
            "JOIN FETCH r.tickets t " +
            "JOIN FETCH t.price " +
            "WHERE r.id = :id")
    Optional<Reservation> findReservationByIdWithTicketsWithLocking(Long id);

}
