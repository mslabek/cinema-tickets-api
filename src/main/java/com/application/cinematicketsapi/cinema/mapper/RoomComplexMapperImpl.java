package com.application.cinematicketsapi.cinema.mapper;

import com.application.cinematicketsapi.cinema.dto.RoomWithSeatStatusDto;
import com.application.cinematicketsapi.cinema.dto.SeatWithStatusDto;
import com.application.cinematicketsapi.cinema.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RoomComplexMapperImpl implements RoomComplexMapper {

    private final SeatComplexMapper seatMapper;

    public RoomWithSeatStatusDto roomToDtoWithSeatStatus(Room room) {
        List<SeatWithStatusDto> seats = room.getRows()
                                            .stream()
                                            .map(seatMapper::rowToSeatDtosWithSeatStatus)
                                            .flatMap(Collection::stream)
                                            .toList();
        return new RoomWithSeatStatusDto(room.getId(), seats);
    }

}
