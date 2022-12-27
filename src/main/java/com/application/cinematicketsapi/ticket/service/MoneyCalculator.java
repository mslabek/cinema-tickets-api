package com.application.cinematicketsapi.ticket.service;

import com.application.cinematicketsapi.common.exception.DataInconsistencyException;
import com.application.cinematicketsapi.ticket.exception.ReservationPaymentException;
import com.application.cinematicketsapi.ticket.model.Money;
import com.application.cinematicketsapi.ticket.model.Reservation;
import com.application.cinematicketsapi.ticket.model.Ticket;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Service
public class MoneyCalculator {

    public Money calculateTotal(List<Money> prices) {
        if (!oneCurrencyUsed(prices)) {
            throw new DataInconsistencyException("Currency not set or multiple currencies set in one reservation");
        }
        Currency currency = prices.get(0)
                                  .getCurrency();
        BigDecimal amount = calculateTotalAmount(prices);
        return Money.builder()
                    .currency(currency)
                    .amount(amount)
                    .build();
    }

    public Money calculateTotalPrice(Reservation reservation) {
        List<Money> allPrices = reservation.getTickets()
                                           .stream()
                                           .map(Ticket::getPrice)
                                           .toList();
        return calculateTotal(allPrices);
    }

    private BigDecimal calculateTotalAmount(List<Money> prices) {
        return prices.stream()
                     .map(Money::getAmount)
                     .reduce(BigDecimal::add)
                     .orElseThrow(() -> new DataInconsistencyException("No money amounts to add"));
    }

    private boolean oneCurrencyUsed(List<Money> prices) {
        long distinctCurrenciesCount = prices.stream()
                                             .map(Money::getCurrency)
                                             .distinct()
                                             .count();
        return distinctCurrenciesCount == 1;
    }

    public boolean valuesEqual(Money money1, Money money2) {
        if (!money1.getCurrency()
                   .equals(money2.getCurrency())) {
            throw new ReservationPaymentException("Given currency is different than required.");
        }
        return money1.getAmount()
                     .compareTo(money2.getAmount()) == 0;
    }

}
