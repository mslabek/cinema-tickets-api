package com.application.cinematicketsapi.demo.service;

import com.application.cinematicketsapi.cinema.model.Seat;
import com.application.cinematicketsapi.cinema.service.SeatService;
import com.application.cinematicketsapi.demo.model.TicketDemoData;
import com.application.cinematicketsapi.screening.model.Screening;
import com.application.cinematicketsapi.screening.service.ScreeningService;
import com.application.cinematicketsapi.ticket.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketObjectMother {

    private final SeatService seatService;
    private final ScreeningService screeningService;

    public List<Ticket> generateTickets(List<TicketDemoData> ticketData) {
        return ticketData.stream()
                         .map(this::generateSoldTicket)
                         .toList();
    }

    public Ticket generateSoldTicket(TicketDemoData ticketData) {
        Ticket ticket = new Ticket();

        Seat seat = seatService.getSeat(ticketData.getScreeningId(), ticketData.getRow(), ticketData.getColumn());
        Screening screening = screeningService.getScreening(ticketData.getScreeningId());

        seat.addTicket(ticket);
        ticket.setScreening(screening);
        ticket.setStatus(ticketData.getStatus());

        return ticket;
    }

}
