package com.application.cinematicketsapi.infrastructure.exception;

/**
 * Interface to be implemented by exceptions that can be shown to the client. The exceptions that have to be hidden from
 * the user can be obscured by throwing an exception implementing this interface with limited information.
 */
public interface ApiLevelException {

    String getMessage();

}
