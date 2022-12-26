package com.application.cinematicketsapi.ticket.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReservationCreationForm {

    @Min(value = 1, message = "The screening id cannot be smaller than 1")
    private final Long screeningId;

    @Length(min = 3, message = "The name has to be at least 3 characters long.")
    @Pattern(regexp = "^\\p{Lu}.*", message = "The name has to start with a capital letter.")
    @Pattern(regexp = "\\p{L}*", message = "The name can contain only alphabet letters.")
    private final String name;

    @Length(min = 3, message = "The surname has to be at least 3 characters long.")
    @Pattern(regexp = "^\\p{Lu}.*", message = "The name has to start with a capital letter.")
    @Pattern(regexp = "\\p{L}*(-\\p{Lu}\\p{Ll}*)?", message = "The surname can consist of one part or two parts " +
            "separated with single dash. The second part has to start with a capital letter")
    private final String surname;

    @Valid
    @NotEmpty(message = "The reservation has to apply to at least one seat")
    private final List<TicketCreationForm> tickets;

}
