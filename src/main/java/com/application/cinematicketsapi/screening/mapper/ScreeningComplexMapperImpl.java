package com.application.cinematicketsapi.screening.mapper;

import com.application.cinematicketsapi.cinema.dto.RoomWithSeatStatusDto;
import com.application.cinematicketsapi.cinema.mapper.RoomComplexMapper;
import com.application.cinematicketsapi.screening.dto.ScreeningFullDto;
import com.application.cinematicketsapi.screening.model.Screening;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScreeningComplexMapperImpl implements ScreeningComplexMapper {

    private final RoomComplexMapper roomComplexMapper;

    @Override
    public ScreeningFullDto screeningToFullDto(Screening screening) {
        if (screening == null) {
            return null;
        }
        RoomWithSeatStatusDto room = roomComplexMapper.roomToDtoWithSeatStatus(screening.getRoom());

        return new ScreeningFullDto(screening.getId(), screening.getBeginning(), screening.getEnding(), screening.getMovie()
                                                                                                                 .getTitle(), room);
    }

}
