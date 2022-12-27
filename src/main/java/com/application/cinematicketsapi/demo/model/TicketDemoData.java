package com.application.cinematicketsapi.demo.model;

import com.application.cinematicketsapi.ticket.model.TicketStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDemoData {

    private Long screeningId;
    private int row;
    private int column;
    private TicketStatus status;

}
