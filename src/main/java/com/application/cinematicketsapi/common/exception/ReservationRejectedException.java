package com.application.cinematicketsapi.common.exception;

import com.application.cinematicketsapi.infrastructure.exception.ApiLevelException;

public class ReservationRejectedException extends IllegalArgumentException implements ApiLevelException {

    public ReservationRejectedException(String s) {
        super(s);
    }

}
