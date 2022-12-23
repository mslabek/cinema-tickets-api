package com.application.cinematicketsapi.ticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Entity representing monetary value.
 *
 * @see <a href="https://martinfowler.com/eaaCatalog/money.html">Martin Fowler's article on representing monetary
 * values as objects</a>
 */
@Entity
@Getter
@Setter
public class Money {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Currency currency;
    private BigDecimal amount;

}
