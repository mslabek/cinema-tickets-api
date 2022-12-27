package com.application.cinematicketsapi.ticket.dto;

import java.math.BigDecimal;
import java.util.Currency;

public record MoneyDto(Currency currency, BigDecimal amount) {
}
