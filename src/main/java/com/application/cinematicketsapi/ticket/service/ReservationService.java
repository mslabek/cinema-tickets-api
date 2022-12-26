package com.application.cinematicketsapi.ticket.service;

import com.application.cinematicketsapi.common.exception.DataInconsistencyException;
import com.application.cinematicketsapi.common.exception.ResourceNotFoundException;
import com.application.cinematicketsapi.screening.model.Screening;
import com.application.cinematicketsapi.screening.service.ScreeningService;
import com.application.cinematicketsapi.ticket.dto.ReservationDto;
import com.application.cinematicketsapi.ticket.exception.ReservationPaymentException;
import com.application.cinematicketsapi.ticket.form.PaymentForm;
import com.application.cinematicketsapi.ticket.form.ReservationCreationForm;
import com.application.cinematicketsapi.ticket.mapper.MoneyMapper;
import com.application.cinematicketsapi.ticket.mapper.ReservationMapper;
import com.application.cinematicketsapi.ticket.model.*;
import com.application.cinematicketsapi.ticket.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReservationDtoService {

    private final ReservationFactory reservationFactory;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;
    private final ScreeningService screeningService;
    private final MoneyMapper moneyMapper;
    private final MoneyCalculator moneyCalculator;

    @Override
    @Transactional
    public ReservationDto createReservation(ReservationCreationForm form) {
        Screening screening = screeningService.getScreeningForTicketCreationWithLocking(form.getScreeningId());
        Reservation reservation = saveReservation(reservationFactory.buildReservation(form, screening));
        return reservationMapper.mapReservationToReservationDto(reservation);
    }

    @Override
    @Transactional
    public ReservationDto processReservationPayment(Long reservationId, PaymentForm form) {
        Reservation reservation = getReservationWithTickets(reservationId);
        validateActiveReservation(reservation);
        validatePaymentIsCorrect(form, moneyCalculator.calculateTotalPrice(reservation));
        updateTicketsAfterPayment(reservation.getTickets());
        updateReservationStatusAfterPayment(reservation);
        return reservationMapper.mapReservationToReservationDto(reservation);
    }


    private Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    private void updateReservationStatusAfterPayment(Reservation reservation) {
        reservation.setStatus(ReservationStatus.FULFILLED);
    }


    private void updateTicketsAfterPayment(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            ticket.setStatus(TicketStatus.SOLD);
        }
    }

    private Reservation getReservationWithTickets(Long id) {
        return reservationRepository.findReservationByIdWithTicketsWithLocking(id)
                                    .orElseThrow(() -> new ResourceNotFoundException("Reservation not found in the repository."));
    }


    private void validatePaymentIsCorrect(PaymentForm form, Money requiredPayment) {
        Money recievedMoney = moneyMapper.mapPaymentFormToMoney(form);
        if (!moneyCalculator.valuesEqual(recievedMoney, requiredPayment)) {
            throw new ReservationPaymentException("Incorrect amount of money given.");
        }
    }

    private void validateActiveReservation(Reservation reservation) {
        switch (reservation.getStatus()) {
            case EXPIRED -> throw new ReservationPaymentException("Reservation is already expired.");
            case FULFILLED -> throw new ReservationPaymentException("Reservation has already been paid for.");
        }
        validateReservedTickets(reservation);
    }

    private void validateReservedTickets(Reservation reservation) {
        for (Ticket ticket : reservation.getTickets()) {
            switch (ticket.getStatus()) {
                case EXPIRED ->
                        throw new DataInconsistencyException("A ticket is expired while reservation is active.");
                case SOLD -> throw new ReservationPaymentException("Reservation is already paid for.");
            }
        }
    }

}
