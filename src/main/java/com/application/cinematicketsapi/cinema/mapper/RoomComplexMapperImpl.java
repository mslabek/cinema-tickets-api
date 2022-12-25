package com.application.cinematicketsapi.cinema.mapper;

import com.application.cinematicketsapi.cinema.dto.RoomWithSeatStatusDto;
import com.application.cinematicketsapi.cinema.dto.SeatStatus;
import com.application.cinematicketsapi.cinema.dto.SeatWithStatusDto;
import com.application.cinematicketsapi.cinema.model.Room;
import com.application.cinematicketsapi.cinema.model.Row;
import com.application.cinematicketsapi.cinema.model.Seat;
import com.application.cinematicketsapi.ticket.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class RoomComplexMapperImpl implements RoomComplexMapper {

    public RoomWithSeatStatusDto roomToDtoWithSeatStatus(Room room) {
        List<SeatWithStatusDto> seats = room.getRows()
                                            .stream()
                                            .map(this::rowToSeatDtosWithSeatStatus)
                                            .flatMap(Collection::stream)
                                            .toList();
        return new RoomWithSeatStatusDto(room.getId(), seats);
    }

    private List<SeatWithStatusDto> rowToSeatDtosWithSeatStatus(Row row) {
        return row.getSeats()
                  .stream()
                  .map(seat -> seatToSeatDtoWithSeatStatus(seat, row.getNumber()))
                  .toList();
    }

    private SeatWithStatusDto seatToSeatDtoWithSeatStatus(Seat seat, int row) {
        SeatStatus status = establishSeatStatus(seat);
        return new SeatWithStatusDto(seat.getId(), row, seat.getColumn(), status);
    }

    private SeatStatus establishSeatStatus(Seat seat) {
        List<Ticket> tickets = seat.getTickets();
        // TODO
        return SeatStatus.FREE;
    }

}
