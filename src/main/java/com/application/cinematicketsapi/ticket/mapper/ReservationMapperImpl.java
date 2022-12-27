package com.application.cinematicketsapi.ticket.mapper;

import com.application.cinematicketsapi.ticket.dto.MoneyDto;
import com.application.cinematicketsapi.ticket.dto.ReservationDto;
import com.application.cinematicketsapi.ticket.dto.TicketDto;
import com.application.cinematicketsapi.ticket.model.Reservation;
import com.application.cinematicketsapi.ticket.model.ReservationStatus;
import com.application.cinematicketsapi.ticket.service.MoneyCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationMapperImpl implements ReservationMapper {

    private final TicketMapper ticketMapper;
    private final MoneyMapper moneyMapper;
    private final MoneyCalculator calculator;

    public ReservationDto mapReservationToReservationDto(Reservation reservation) {
        Long id = reservation.getId();
        String name = reservation.getName();
        String surname = reservation.getSurname();
        ReservationStatus status = reservation.getStatus();
        LocalDateTime createdAt = reservation.getCreatedAt();
        LocalDateTime expiresAt = reservation.getExpiresAt();
        List<TicketDto> tickets = reservation.getTickets()
                                             .stream()
                                             .map(ticketMapper::ticketToTicketDto)
                                             .toList();
        MoneyDto totalAmountToPay = moneyMapper.moneyToMoneyDto(calculator.calculateTotalPrice(reservation));

        return new ReservationDto(id, name, surname, status, createdAt, expiresAt, totalAmountToPay, tickets);
    }

}
