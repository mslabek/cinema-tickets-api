package com.application.cinematicketsapi.ticket.service;

import com.application.cinematicketsapi.common.exception.DataInconsistencyException;
import com.application.cinematicketsapi.ticket.model.Money;
import com.application.cinematicketsapi.ticket.model.TicketType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@Service
public class TicketPricingStrategy {

    public Money getPrice(TicketType type) {
        Currency currency = Currency.getInstance("PLN");
        BigDecimal amount;

        switch (type) {
            case ADULT -> amount = BigDecimal.valueOf(25.00)
                                             .setScale(2, RoundingMode.FLOOR);
            case STUDENT -> amount = BigDecimal.valueOf(18.00)
                                               .setScale(2, RoundingMode.FLOOR);
            case CHILD -> amount = BigDecimal.valueOf(12.50)
                                             .setScale(2, RoundingMode.FLOOR);
            default -> throw new DataInconsistencyException("Ticket type not recognized.");
        }

        return Money.builder()
                    .currency(currency)
                    .amount(amount)
                    .build();
    }

}
