package com.application.cinematicketsapi.ticket.service;

import com.application.cinematicketsapi.screening.model.Screening;
import com.application.cinematicketsapi.screening.service.ScreeningService;
import com.application.cinematicketsapi.ticket.dto.ReservationDto;
import com.application.cinematicketsapi.ticket.form.ReservationCreationForm;
import com.application.cinematicketsapi.ticket.mapper.ReservationMapper;
import com.application.cinematicketsapi.ticket.model.Reservation;
import com.application.cinematicketsapi.ticket.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReservationDtoService {

    private final ReservationFactory reservationFactory;
    private final ReservationMapper reservationMapper;
    private final ReservationRepository reservationRepository;
    private final ScreeningService screeningService;

    @Override
    @Transactional
    public ReservationDto createReservation(ReservationCreationForm form) {
        Screening screening = screeningService.getScreeningForTicketCreationWithLocking(form.getScreeningId());
        Reservation reservation = saveReservation(reservationFactory.buildReservation(form, screening));
        return reservationMapper.mapReservationToReservationDto(reservation);
    }

    private Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

}
