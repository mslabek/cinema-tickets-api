package com.application.cinematicketsapi.common.exception;

import com.application.cinematicketsapi.infrastructure.exception.ServerLevelException;

public class DataNotFilteredException extends RuntimeException implements ServerLevelException {

    public DataNotFilteredException(String message) {
        super(message);
    }

}
