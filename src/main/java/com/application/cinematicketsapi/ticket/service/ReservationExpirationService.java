package com.application.cinematicketsapi.ticket.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReservationExpirationService {

    private final ReservationService reservationService;
    private final Logger logger = LoggerFactory.getLogger(ReservationExpirationService.class);

    @Scheduled(cron = "0 * * * * *")
    public void rem() {
        int expiredReservationsCount = reservationService.invalidateExpiredReservations();
        String message = String.format("Expired %d reservations", expiredReservationsCount);
        logger.info(message);
    }

}
