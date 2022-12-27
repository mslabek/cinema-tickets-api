package com.application.cinematicketsapi.ticket.form;

import com.application.cinematicketsapi.common.validator.ValidEnum;
import com.application.cinematicketsapi.ticket.model.TicketType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketCreationForm {

    @Min(value = 1, message = "The seat id cannot be smaller than 1")
    @Schema(description = "Seat id", example = "4")
    private final Long seatId;

    @ValidEnum(targetClassType = TicketType.class)
    @Schema(description = "Ticket type", example = "ADULT")
    private final String type;

}
