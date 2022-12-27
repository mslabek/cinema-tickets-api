package com.application.cinematicketsapi.demo.service;

import com.application.cinematicketsapi.cinema.model.Room;
import com.application.cinematicketsapi.cinema.service.RoomService;
import com.application.cinematicketsapi.demo.model.ScreeningDemoData;
import com.application.cinematicketsapi.screening.model.Movie;
import com.application.cinematicketsapi.screening.model.Screening;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScreeningObjectMother {

    private final RoomService roomService;

    public Screening generateScreening(ScreeningDemoData screeningData, Movie movie) {
        Screening screening = new Screening();
        movie.addScreening(screening);

        Room room = roomService.getRoom(screeningData.getRoomId());
        room.addScreening(screening);

        screening.setBeginning(screeningData.getBeginning());
        screening.setEnding(screeningData.getEnding());

        return screening;
    }

}
