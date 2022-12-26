package com.application.cinematicketsapi.ticket.service;

import com.application.cinematicketsapi.common.exception.DataInconsistencyException;
import com.application.cinematicketsapi.ticket.model.Money;
import com.application.cinematicketsapi.ticket.model.TicketType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;

@Service
public class TicketPricingStrategy {

    public Money getPrice(TicketType type) {
        Currency currency = Currency.getInstance("PLN");
        BigDecimal amount;

        switch (type) {
            case ADULT -> amount = BigDecimal.valueOf(25);
            case STUDENT -> amount = BigDecimal.valueOf(18);
            case CHILD -> amount = BigDecimal.valueOf(12.5);
            default -> throw new DataInconsistencyException("Ticket type not recognized.");
        }

        return Money.builder()
                    .currency(currency)
                    .amount(amount)
                    .build();
    }

}
