package com.application.cinematicketsapi.ticket.form;

import com.application.cinematicketsapi.ticket.validator.ValidCurrency;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PaymentForm {

    @Min(value = 0, message = "The payment amount cannot be a negative number")
    private final BigDecimal amount;

    @ValidCurrency
    private final String currency;

}
