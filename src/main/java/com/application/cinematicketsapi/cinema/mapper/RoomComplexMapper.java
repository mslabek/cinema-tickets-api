package com.application.cinematicketsapi.cinema.mapper;

import com.application.cinematicketsapi.cinema.dto.RoomWithSeatStatusDto;
import com.application.cinematicketsapi.cinema.model.Room;

public interface RoomComplexMapper {

    RoomWithSeatStatusDto roomToDtoWithSeatStatus(Room room);

}
