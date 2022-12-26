package com.application.cinematicketsapi.ticket.service;

import com.application.cinematicketsapi.cinema.dto.SeatStatus;
import com.application.cinematicketsapi.cinema.model.Seat;
import com.application.cinematicketsapi.cinema.service.SeatService;
import com.application.cinematicketsapi.screening.model.Screening;
import com.application.cinematicketsapi.screening.service.ScreeningService;
import com.application.cinematicketsapi.ticket.exception.ReservationRejectedException;
import com.application.cinematicketsapi.ticket.form.TicketCreationForm;
import com.application.cinematicketsapi.ticket.model.Ticket;
import com.application.cinematicketsapi.ticket.model.TicketStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TicketFactory {

    private final SeatService seatService;
    private final ScreeningService screeningService;
    private final TicketPricingStrategy pricingStrategy;

    public Ticket buildTicket(TicketCreationForm form, Screening screening) {
        Seat seat = screeningService.getSeat(form.getSeatId(), screening);
        validateAvailableSeat(seat);

        Ticket ticket = Ticket.builder()
                              .type(form.getType())
                              .price(pricingStrategy.getPrice(form.getType()))
                              .screening(screening)
                              .status(TicketStatus.RESERVED)
                              .build();

        /*
           I think hibernate fetches the ticket list as immutable list and I don't know why. This is copying this
           list to a mutable list and setting it as the seat's tickets. This is a temporary hack, it cannot stay like
           this without explanation.
         */
        seat.setTickets(new ArrayList<>(seat.getTickets()));

        seat.addTicket(ticket);

        return ticket;
    }

    private void validateAvailableSeat(Seat seat) {
        if (seatService.establishSeatStatus(seat)
                       .equals(SeatStatus.TAKEN)) {
            throw new ReservationRejectedException("Seat already taken. Please choose another seat.");
        }
    }

}
