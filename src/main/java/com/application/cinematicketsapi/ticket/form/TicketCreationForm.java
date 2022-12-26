package com.application.cinematicketsapi.ticket.form;

import com.application.cinematicketsapi.common.validator.ValidEnum;
import com.application.cinematicketsapi.ticket.model.TicketType;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketCreationForm {

    @Min(value = 1, message = "The seat id cannot be smaller than 1")
    private final Long seatId;

    @ValidEnum(targetClassType = TicketType.class)
    private final String type;

}
