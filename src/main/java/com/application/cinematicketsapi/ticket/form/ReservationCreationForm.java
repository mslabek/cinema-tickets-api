package com.application.cinematicketsapi.ticket.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReservationCreationForm {

    private final Long screeningId;
    private final String name;
    private final String surname;
    private final List<TicketCreationForm> tickets;

}
