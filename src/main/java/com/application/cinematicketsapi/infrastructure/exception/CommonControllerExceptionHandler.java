package com.application.cinematicketsapi.infrastructure.exception;

import com.application.cinematicketsapi.common.exception.DataInconsistencyException;
import com.application.cinematicketsapi.common.exception.DataNotFilteredException;
import com.application.cinematicketsapi.common.exception.ResourceNotFoundException;
import com.application.cinematicketsapi.ticket.exception.ReservationPaymentException;
import com.application.cinematicketsapi.ticket.exception.ReservationRejectedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
        ApiError error = new ApiError(getTimeNowTruncated(), status.value(), status.name(), ex.getMessage(), request.getServletPath());
        return generateDefaultResponse(error, status);
    }

    @ExceptionHandler({ReservationRejectedException.class, ReservationPaymentException.class})
    public ResponseEntity<Object> handleBadRequests(ApiLevelException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = new ApiError(getTimeNowTruncated(), status.value(), status.name(), ex.getMessage(), request.getServletPath());
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
        ApiError error = new ApiError(getTimeNowTruncated(), status.value(), status.name(), message, details, request.getServletPath());

        return generateDefaultResponse(error, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidationError(ConstraintViolationException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<ConstraintViolation<?>> violations = ex.getConstraintViolations()
                                                    .stream()
                                                    .toList();
        String message = "Validation failed with: " + violations.size() + " errors.";
        List<String> details = violations.stream()
                                         .map(ConstraintViolation::getMessage)
                                         .toList();
        ApiError error = new ApiError(getTimeNowTruncated(), status.value(), status.name(), message, details, request.getServletPath());
        return generateDefaultResponse(error, status);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleBadRequests(HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "The request body is malformed.";
        ApiError error = new ApiError(getTimeNowTruncated(), status.value(), status.name(), message, request.getServletPath());
        return generateDefaultResponse(error, status);
    }

    @ExceptionHandler({DataInconsistencyException.class, DataNotFilteredException.class})
    ResponseEntity<Object> handleServerExceptions(HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String internalErrorMessage = "Something went wrong. Please try again.";
        ApiError error = new ApiError(getTimeNowTruncated(), status.value(), status.name(), internalErrorMessage, request.getServletPath());
        return generateDefaultResponse(error, status);
    }

    private ResponseEntity<Object> generateDefaultResponse(ApiError error, HttpStatus status) {
        return new ResponseEntity<>(error, new HttpHeaders(), status);
    }

    private LocalDateTime getTimeNowTruncated() {
        return LocalDateTime.now()
                            .truncatedTo(ChronoUnit.SECONDS);
    }

}
