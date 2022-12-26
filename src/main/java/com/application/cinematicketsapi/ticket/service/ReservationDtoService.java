package com.application.cinematicketsapi.ticket.service;

import com.application.cinematicketsapi.ticket.dto.ReservationDto;
import com.application.cinematicketsapi.ticket.form.PaymentForm;
import com.application.cinematicketsapi.ticket.form.ReservationCreationForm;

public interface ReservationDtoService {

    ReservationDto createReservation(ReservationCreationForm form);

    ReservationDto processReservationPayment(Long reservationId, PaymentForm form);

}
