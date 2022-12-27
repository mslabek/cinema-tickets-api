package com.application.cinematicketsapi.ticket.controller;

import com.application.cinematicketsapi.ticket.dto.ReservationDto;
import com.application.cinematicketsapi.ticket.form.PaymentForm;
import com.application.cinematicketsapi.ticket.form.ReservationCreationForm;
import com.application.cinematicketsapi.ticket.service.ReservationDtoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Reservations", description = "Operations referring to reservations")
@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationDtoService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a reservation")
    @ApiResponse(responseCode = "201", description = "Reservation created successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "404", description = "Screening or specified seat not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public ReservationDto createReservation(@RequestBody @Valid ReservationCreationForm form) {
        return reservationService.createReservation(form);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Processes payment for a reservation")
    @Parameter(name = "id", description = "the id of the reservation to be paid for", example = "1")
    @ApiResponse(responseCode = "200", description = "Reservation and associated tickets updated successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "404", description = "Reservation with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public ReservationDto processReservationPayment(@PathVariable Long id, @RequestBody @Valid PaymentForm form) {
        return reservationService.processReservationPayment(id, form);
    }

}
