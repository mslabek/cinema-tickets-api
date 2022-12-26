package com.application.cinematicketsapi.infrastructure.exception;

import com.application.cinematicketsapi.common.exception.DataInconsistencyException;
import com.application.cinematicketsapi.common.exception.DataNotFilteredException;
import com.application.cinematicketsapi.common.exception.ResourceNotFoundException;
import com.application.cinematicketsapi.ticket.exception.ReservationPaymentException;
import com.application.cinematicketsapi.ticket.exception.ReservationRejectedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class containing methods handling exceptions thrown by {@code controllers}. This mechanism allows intercepting
 * specified exceptions globally (for all controllers) thus removing the need for code duplication. All
 * {@link ApiLevelException ApiLevelExceptions} should have an {@link ExceptionHandler}.
 */
@ControllerAdvice
public class CommonControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ApiLevelException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError error = new ApiError(LocalDateTime.now(), status.value(), status.name(), ex.getMessage(), request.getServletPath());
        return generateDefaultResponse(error, status);
    }

    @ExceptionHandler({ReservationRejectedException.class, ReservationPaymentException.class})
    public ResponseEntity<Object> handleBadRequests(ApiLevelException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = new ApiError(LocalDateTime.now(), status.value(), status.name(), ex.getMessage(), request.getServletPath());
        return generateDefaultResponse(error, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationError(MethodArgumentNotValidException ex,
                                                        HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Validation failed with: " + ex.getBindingResult()
                                                        .getErrorCount() + " errors.";
        List<String> details = ex.getAllErrors()
                                 .stream()
                                 .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                 .toList();
        ApiError error = new ApiError(LocalDateTime.now(), status.value(), status.name(), message, details, request.getServletPath());
        return generateDefaultResponse(error, status);
    }

    @ExceptionHandler({DataInconsistencyException.class, DataNotFilteredException.class})
    ResponseEntity<Object> handleServerExceptions(ServerLevelException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String internalErrorMessage = "Something went wrong. Please try again.";
        ApiError error = new ApiError(LocalDateTime.now(), status.value(), status.name(), internalErrorMessage, request.getServletPath());
        return generateDefaultResponse(error, status);
    }

    private ResponseEntity<Object> generateDefaultResponse(ApiError error, HttpStatus status) {
        return new ResponseEntity<>(error, new HttpHeaders(), status);
    }

}
