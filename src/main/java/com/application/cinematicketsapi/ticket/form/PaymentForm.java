package com.application.cinematicketsapi.ticket.form;

import com.application.cinematicketsapi.ticket.validator.ValidCurrency;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PaymentForm {


    @Min(value = 0, message = "The payment amount cannot be a negative number")
    @Schema(description = "Amount of money", example = "25")
    private final BigDecimal amount;

    @ValidCurrency
    @Schema(description = "Currency code", example = "PLN")
    private final String currency;

}
