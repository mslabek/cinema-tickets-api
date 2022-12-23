package com.application.cinematicketsapi.demo.service;

import com.application.cinematicketsapi.cinema.model.Room;
import com.application.cinematicketsapi.cinema.service.RoomService;
import com.application.cinematicketsapi.demo.model.CinemaDemoData;
import com.application.cinematicketsapi.demo.model.MovieDemoData;
import com.application.cinematicketsapi.screening.model.Movie;
import com.application.cinematicketsapi.screening.model.Screening;
import com.application.cinematicketsapi.screening.service.MovieService;
import com.application.cinematicketsapi.screening.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Profile("demo")
@RequiredArgsConstructor
public class DataGenerator {

    private final CinemaObjectMother cinemaObjectMother;
    private final MovieObjectMother movieObjectMother;
    private final ScreeningObjectMother screeningObjectMother;
    private final MovieService movieService;
    private final ScreeningService screeningService;
    private final RoomService roomService;

    public void generateAndPersistRooms(CinemaDemoData cinemaData) {
        generateRooms(cinemaData.getRooms(), cinemaData.getSeatRows(), cinemaData.getSeatColumns()).forEach(roomService::saveRoom);
    }

    public void generateAndPersistMovies(List<MovieDemoData> moviesConfig) {
        generateMovies(moviesConfig).forEach(movieService::saveMovie);
    }

    @Transactional
    public void generateAndPersistScreenings(List<MovieDemoData> moviesConfig) {
        generateScreenings(moviesConfig).forEach(screeningService::saveScreening);
    }

    public List<Room> generateRooms(int roomCount, int seatRows, int seatColumns) {
        return cinemaObjectMother.generateRooms(roomCount, seatRows, seatColumns);
    }

    public List<Movie> generateMovies(List<MovieDemoData> moviesConfig) {
        return movieObjectMother.generateMovies(moviesConfig);
    }

    public List<Screening> generateScreenings(List<MovieDemoData> moviesData) {
        return moviesData.stream().map(this::generateScreeningsForOneMovie).flatMap(Collection::stream).toList();
    }

    private List<Screening> generateScreeningsForOneMovie(MovieDemoData movieData) {
        Movie movie = movieService.getMovie(movieData.getTitle());
        return movieData.getScreenings()
                        .stream()
                        .map(screeningData -> screeningObjectMother.generateScreening(screeningData, movie))
                        .toList();
    }

}
