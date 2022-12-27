package com.application.cinematicketsapi.common.exception;

import com.application.cinematicketsapi.infrastructure.exception.ServerLevelException;

public class DataInconsistencyException extends RuntimeException implements ServerLevelException {

    public DataInconsistencyException(String message) {
        super(message);
    }

}
