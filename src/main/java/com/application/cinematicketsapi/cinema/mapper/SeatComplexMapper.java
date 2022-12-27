package com.application.cinematicketsapi.cinema.mapper;

import com.application.cinematicketsapi.cinema.dto.SeatWithStatusDto;
import com.application.cinematicketsapi.cinema.model.Row;

import java.util.List;

public interface SeatComplexMapper {

    List<SeatWithStatusDto> rowToSeatDtosWithSeatStatus(Row row);

}
