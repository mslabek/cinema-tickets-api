package com.application.cinematicketsapi.cinema.service;

import com.application.cinematicketsapi.cinema.dto.SeatStatus;
import com.application.cinematicketsapi.cinema.model.Seat;
import com.application.cinematicketsapi.cinema.repository.SeatRepository;
import com.application.cinematicketsapi.common.exception.DataInconsistencyException;
import com.application.cinematicketsapi.common.exception.DataNotFilteredException;
import com.application.cinematicketsapi.common.exception.ResourceNotFoundException;
import com.application.cinematicketsapi.screening.model.Screening;
import com.application.cinematicketsapi.ticket.model.Ticket;
import com.application.cinematicketsapi.ticket.model.TicketStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    /**
     * Establishes status of a {@link Seat} if it contains only one relevant {@link  Ticket}.
     * <p>
     * This method requires filtering out the following tickets in the {@code Seat}:
     * <ul>
     *     <li>
     *         tickets that are not associated with a particular {@code Screening}
     *     </li>
     *     <li>
     *         tickets with the {@code EXPIRED} {@link TicketStatus}
     *     </li>
     * </ul>
     *
     * @param seat the seat containing only one relevant {@code Ticket}
     * @return the established seat status
     */
    public SeatStatus establishSeatStatus(Seat seat) {
        List<Ticket> tickets = seat.getTickets();
        if (tickets.size() > 1) {
            throw new DataInconsistencyException("More than one relevant ticket exists");
        }
        if (tickets.isEmpty()) {
            return SeatStatus.FREE;
        }
        Ticket ticket = tickets.get(0);
        if (ticket.getStatus() == TicketStatus.EXPIRED) {
            throw new DataNotFilteredException("Relevant ticket is expired");
        }
        if (ticket.getStatus() == TicketStatus.SOLD || ticket.getStatus() == TicketStatus.RESERVED) {
            return SeatStatus.TAKEN;
        } else {
            throw new DataInconsistencyException("Unexpected scenario. Ticket status unknown. Was there anything added to the " +
                    "TicketStatus enum?");
        }
    }

}
