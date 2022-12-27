package com.application.cinematicketsapi.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class containing information about errors that occurred during the processing of client's requests. It can be used to
 * limit the internal error information for the client (for example for security reasons) but also provide enough
 * information for the client to know what to do next.
 */
@Getter
@Setter
public class ApiError {

    private final LocalDateTime time;
    private final Integer statusCode;
    private final String statusName;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<String> details;

    private final String path;

    /**
     * Constructor for Exceptions where there are details to be listed. For example when there are many validation
     * errors causing one exception.
     *
     * @param time       the moment when the error was constructed
     * @param statusCode the code of the response status
     * @param statusName the name of the response status
     * @param message    the message of the thrown exception
     * @param details    the details about the thrown exception
     * @param path       the path of the request which caused the exception
     */
    public ApiError(LocalDateTime time, Integer statusCode, String statusName, String message, List<String> details,
                    String path) {
        this.time = time;
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.message = message;
        this.details = details;
        this.path = path;
    }

    /**
     * Constructor for Exceptions where there are no details to be listed. This can be useful for simple exceptions such
     * as 404 errors, which are usually self-describing.
     *
     * @param time       the moment when the error was constructed
     * @param statusCode the code of the response status
     * @param statusName the name of the response status
     * @param message    the message of the thrown exception
     * @param path       the path of the request which caused the exception
     */
    public ApiError(LocalDateTime time, Integer statusCode, String statusName, String message, String path) {
        this.time = time;
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.message = message;
        this.path = path;
        this.details = null;
    }

}
