package com.application.cinematicketsapi.ticket.mapper;

import com.application.cinematicketsapi.ticket.dto.ReservationDto;
import com.application.cinematicketsapi.ticket.model.Reservation;

public interface ReservationMapper {

    ReservationDto mapReservationToReservationDto(Reservation reservation);

}
