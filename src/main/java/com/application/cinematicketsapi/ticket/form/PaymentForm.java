package com.application.cinematicketsapi.ticket.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@AllArgsConstructor
public class PaymentForm {

    private final BigDecimal amount;
    private final Currency currency;

}
