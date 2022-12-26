package com.application.cinematicketsapi.infrastructure.exception;

import com.application.cinematicketsapi.common.exception.DataInconsistencyException;
import com.application.cinematicketsapi.common.exception.DataNotFilteredException;
import com.application.cinematicketsapi.common.exception.ReservationRejectedException;
import com.application.cinematicketsapi.common.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Class containing methods handling exceptions thrown by {@code controllers}. This mechanism allows intercepting
 * specified exceptions globally (for all controllers) thus removing the need for code duplication. All
 * {@link ApiLevelException ApiLevelExceptions} should have an {@link ExceptionHandler}.
 */
@ControllerAdvice
public class CommonControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ApiLevelException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError error = new ApiError(LocalDateTime.now(), status.value(), status.name(), ex.getMessage(),
                request.getServletPath());
        return generateDefaultResponse(error, status);
    }

    @ExceptionHandler(ReservationRejectedException.class)
    public ResponseEntity<Object> handleReservationRejectedException(ApiLevelException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = new ApiError(LocalDateTime.now(), status.value(), status.name(), ex.getMessage(),
                request.getServletPath());
        return generateDefaultResponse(error, status);
    }

    @ExceptionHandler({DataInconsistencyException.class, DataNotFilteredException.class})
    ResponseEntity<Object> handleServerExceptions(ServerLevelException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String internalErrorMessage = "Something went wrong. Please try again.";
        ApiError error = new ApiError(LocalDateTime.now(), status.value(), status.name(), internalErrorMessage,
                request.getServletPath());
        return generateDefaultResponse(error, status);
    }

    private ResponseEntity<Object> generateDefaultResponse(ApiError error, HttpStatus status) {
        return new ResponseEntity<>(error, new HttpHeaders(), status);
    }

}
