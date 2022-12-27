package com.application.cinematicketsapi.ticket.exception;

import com.application.cinematicketsapi.infrastructure.exception.ApiLevelException;

public class ReservationPaymentException extends IllegalArgumentException implements ApiLevelException {

    public ReservationPaymentException(String s) {
        super(s);
    }

}
