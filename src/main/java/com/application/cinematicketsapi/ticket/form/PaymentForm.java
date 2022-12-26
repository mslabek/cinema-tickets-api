package com.application.cinematicketsapi.ticket.form;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@AllArgsConstructor
public class PaymentForm {

    @Min(value = 0, message = "The payment amount cannot be a negative number")
    private final BigDecimal amount;

    @org.hibernate.validator.constraints.Currency(value = "PLN", message = "The payment currency can only be PLN")
    private final Currency currency;

}
