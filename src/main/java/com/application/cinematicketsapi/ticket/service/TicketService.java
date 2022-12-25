package com.application.cinematicketsapi.ticket.service;

import com.application.cinematicketsapi.ticket.model.Ticket;
import com.application.cinematicketsapi.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

}
