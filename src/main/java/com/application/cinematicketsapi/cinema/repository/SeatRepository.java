package com.application.cinematicketsapi.cinema.repository;

import com.application.cinematicketsapi.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository used to perform CRUD operations on {@link Seat} entities in the persistence layer.
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query("SELECT s FROM Seat s " +
            "JOIN s.row row " +
            "JOIN row.room room " +
            "JOIN room.screenings screening " +
            "WHERE s.column = :column " +
            "AND row.number = :row " +
            "AND screening.id = :screeningId")
    Optional<Seat> findByScreeningRowAndColumn(Long screeningId, Integer row, Integer column);

}
