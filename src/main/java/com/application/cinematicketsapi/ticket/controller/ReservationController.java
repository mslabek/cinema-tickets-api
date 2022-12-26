package com.application.cinematicketsapi.ticket.controller;

import com.application.cinematicketsapi.ticket.dto.ReservationDto;
import com.application.cinematicketsapi.ticket.form.ReservationCreationForm;
import com.application.cinematicketsapi.ticket.service.ReservationDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationDtoService reservationService;

    @PostMapping
    public ReservationDto createReservation(@RequestBody ReservationCreationForm form) {
        return reservationService.createReservation(form);
    }

}
