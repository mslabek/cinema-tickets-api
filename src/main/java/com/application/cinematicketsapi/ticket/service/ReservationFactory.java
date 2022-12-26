package com.application.cinematicketsapi.ticket.service;

import com.application.cinematicketsapi.cinema.model.Row;
import com.application.cinematicketsapi.cinema.model.Seat;
import com.application.cinematicketsapi.screening.model.Screening;
import com.application.cinematicketsapi.ticket.form.ReservationCreationForm;
import com.application.cinematicketsapi.ticket.form.TicketCreationForm;
import com.application.cinematicketsapi.ticket.model.Reservation;
import com.application.cinematicketsapi.ticket.model.ReservationStatus;
import com.application.cinematicketsapi.ticket.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationFactory {

    private final TicketFactory ticketFactory;
    private final RowValidator rowValidator;
    private final ReservationExpiryCalculator expiryCalculator;

    public Reservation buildReservation(ReservationCreationForm form, Screening screening) {
        Reservation reservation = new Reservation();

        List<Ticket> tickets = buildTickets(form.getTickets(), screening);
        for (Ticket t : tickets) {
            reservation.addTicket(t);
        }
        validateModifiedRows(tickets);

        reservation.setCreatedAt(LocalDateTime.now()
                                              .truncatedTo(ChronoUnit.SECONDS));
        reservation.setExpiresAt(expiryCalculator.calculate(reservation.getCreatedAt(), screening.getBeginning()));
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setName(form.getName());
        reservation.setSurname(form.getSurname());

        return reservation;
    }

    private List<Ticket> buildTickets(List<TicketCreationForm> forms, Screening screening) {
        return forms.stream()
                    .map(t -> ticketFactory.buildTicket(t, screening))
                    .toList();
    }

    private void validateModifiedRows(List<Ticket> tickets) {
        getDistinctModifiedRows(tickets).forEach(rowValidator::validateRow);
    }

    private List<Row> getDistinctModifiedRows(List<Ticket> tickets) {
        return tickets.stream()
                      .map(Ticket::getSeat)
                      .map(Seat::getRow)
                      .distinct()
                      .toList();
    }


}
