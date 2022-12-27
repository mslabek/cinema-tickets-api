package com.application.cinematicketsapi.ticket.exception;

import com.application.cinematicketsapi.infrastructure.exception.ApiLevelException;

public class ReservationRejectedException extends IllegalArgumentException implements ApiLevelException {

    public ReservationRejectedException(String s) {
        super(s);
    }

}
