package com.application.cinematicketsapi.ticket.service;

import com.application.cinematicketsapi.cinema.dto.SeatStatus;
import com.application.cinematicketsapi.cinema.model.Row;
import com.application.cinematicketsapi.cinema.model.Seat;
import com.application.cinematicketsapi.cinema.service.SeatService;
import com.application.cinematicketsapi.ticket.exception.ReservationRejectedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RowValidator {

    private final SeatService seatService;

    public void validateRow(Row row) {
        List<SeatStatus> statuses = row.getSeats()
                                       .stream()
                                       .sorted(Comparator.comparingInt(Seat::getColumn))
                                       .map(seatService::establishSeatStatus)
                                       .toList();
        validateRow(statuses);
    }


    private void validateRow(List<SeatStatus> statuses) {
        Pattern state = Pattern.NO_PATTERN;
        for (SeatStatus seatStatus : statuses) {

            if (state == Pattern.NO_PATTERN) {
                if (seatStatus.equals(SeatStatus.TAKEN)) {
                    state = Pattern.ONE_SEAT_TAKEN;
                }

            } else if (state == Pattern.ONE_SEAT_TAKEN) {
                if (seatStatus.equals(SeatStatus.FREE)) {
                    state = Pattern.SEAT_AFTER_TAKEN_IS_FREE;
                } else {
                    state = Pattern.ONE_SEAT_TAKEN;
                }

            } else if (state == Pattern.SEAT_AFTER_TAKEN_IS_FREE) {
                if (seatStatus.equals(SeatStatus.TAKEN)) {
                    throw new ReservationRejectedException("There is a free seat between two taken seats. " +
                            "There cannot be a single place left over in a row between two already taken places.");
                } else {
                    state = Pattern.NO_PATTERN;
                }
            }
        }
    }

    private enum Pattern {
        NO_PATTERN, ONE_SEAT_TAKEN, SEAT_AFTER_TAKEN_IS_FREE
    }

}
