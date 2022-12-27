package com.application.cinematicketsapi.common.exception;

import com.application.cinematicketsapi.infrastructure.exception.ApiLevelException;

import java.util.NoSuchElementException;

/**
 * Exception thrown when a requested resource cannot be found in the database.
 */
public class ResourceNotFoundException extends NoSuchElementException implements ApiLevelException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
