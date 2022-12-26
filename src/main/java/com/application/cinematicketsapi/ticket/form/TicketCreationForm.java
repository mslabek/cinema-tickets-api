package com.application.cinematicketsapi.ticket.form;

import com.application.cinematicketsapi.ticket.model.TicketType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketCreationForm {

    private final Long seatId;
    private final TicketType type;


}
