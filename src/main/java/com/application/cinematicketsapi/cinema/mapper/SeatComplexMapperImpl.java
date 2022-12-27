package com.application.cinematicketsapi.cinema.mapper;

import com.application.cinematicketsapi.cinema.dto.SeatStatus;
import com.application.cinematicketsapi.cinema.dto.SeatWithStatusDto;
import com.application.cinematicketsapi.cinema.model.Row;
import com.application.cinematicketsapi.cinema.model.Seat;
import com.application.cinematicketsapi.cinema.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SeatComplexMapperImpl implements SeatComplexMapper {

    private final SeatService seatService;

    @Override
    public List<SeatWithStatusDto> rowToSeatDtosWithSeatStatus(Row row) {
        return row.getSeats()
                  .stream()
                  .map(seat -> seatToSeatDtoWithSeatStatus(seat, row.getNumber()))
                  .toList();
    }

    private SeatWithStatusDto seatToSeatDtoWithSeatStatus(Seat seat, int row) {
        SeatStatus status = seatService.establishSeatStatus(seat);
        return new SeatWithStatusDto(seat.getId(), row, seat.getColumn(), status);
    }

}
