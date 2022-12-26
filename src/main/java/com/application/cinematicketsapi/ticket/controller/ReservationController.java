package com.application.cinematicketsapi.ticket.controller;

import com.application.cinematicketsapi.ticket.dto.ReservationDto;
import com.application.cinematicketsapi.ticket.form.PaymentForm;
import com.application.cinematicketsapi.ticket.form.ReservationCreationForm;
import com.application.cinematicketsapi.ticket.service.ReservationDtoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationDtoService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDto createReservation(@RequestBody @Valid ReservationCreationForm form) {
        return reservationService.createReservation(form);
    }

    @PatchMapping("/{id}")
    public ReservationDto processReservationPayment(@PathVariable Long id, @RequestBody PaymentForm form) {
        return reservationService.processReservationPayment(id, form);
    }

}
