package com.application.cinematicketsapi.cinema.service;

import com.application.cinematicketsapi.cinema.model.Seat;
import com.application.cinematicketsapi.cinema.repository.SeatRepository;
import com.application.cinematicketsapi.common.exception.ResourceNotFoundException;
import com.application.cinematicketsapi.screening.model.Screening;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * Service handling operations on {@link Seat} objects.
 */
@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    /**
     * Retrieves a {@link Seat} entity from repository.
     *
     * @param id the id of the searched {@code Seat}
     * @return the found {@code Seat} entity
     */
    public Seat getSeat(Long id) {
        return seatRepository.findById(id)
                             .orElseThrow(getResourceNotFoundExceptionSupplier());
    }

    /**
     * Retrieves a {@link Seat} entity from repository based on {@link Screening} and seat data besides the id of the
     * seat.
     * <p>
     * This method is useful for creating test data, because it doesn't require checking in which room the
     * {@code Screening} takes place. The room data is accessed through a couple of joins in the query.
     *
     * @param screeningId the id of the {@link Screening} associated with the {code Seat}
     * @param row         the row of the searched {@code Seat}
     * @param column      the column of the searched {@code Seat}
     * @return the found {@code Seat} entity
     */
    public Seat getSeat(Long screeningId, Integer row, Integer column) {
        return seatRepository.findByScreeningRowAndColumn(screeningId, row, column)
                             .orElseThrow(getResourceNotFoundExceptionSupplier());
    }

    private Supplier<ResourceNotFoundException> getResourceNotFoundExceptionSupplier() {
        return () -> new ResourceNotFoundException("Seat not found in the database");
    }

}
