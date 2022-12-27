package com.application.cinematicketsapi.ticket.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationExpiryCalculator {

    public LocalDateTime calculate(LocalDateTime creationTime, LocalDateTime screeningStartTime) {
        LocalDateTime maxExpirationTime = screeningStartTime.minusMinutes(15);
        if (creationTime.plusMinutes(15)
                        .isAfter(maxExpirationTime)) {
            return maxExpirationTime;
        } else {
            return creationTime.plusMinutes(15);
        }
    }

}
