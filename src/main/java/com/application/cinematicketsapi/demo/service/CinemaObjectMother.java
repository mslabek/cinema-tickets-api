package com.application.cinematicketsapi.demo.service;

import com.application.cinematicketsapi.cinema.model.Room;
import com.application.cinematicketsapi.cinema.model.Row;
import com.application.cinematicketsapi.cinema.model.Seat;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating multiple {@link Room} objects.
 */
@Service
@Profile("demo")
public class CinemaObjectMother {

    /**
     * Generates a list of {@link Room Rooms} with equal number of seats.
     *
     * @param roomCount   the number of rooms to be generated
     * @param seatRows    the number of rows of seats each room is to have
     * @param seatColumns the number of columns of seats each room is to have
     * @return the list of created {@code Room} entities.
     */
    public List<Room> generateRooms(int roomCount, int seatRows, int seatColumns) {
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < roomCount; i++) {
            rooms.add(generateRoom(seatRows, seatColumns));
        }
        return rooms;
    }

    /**
     * Generates a {@link Room} with specified number of rows and columns of seats.
     *
     * @param seatRows    the number of rows of seats each room is to have
     * @param seatColumns the number of columns of seats each room is to have
     * @return the created {@code Room}.
     */
    public Room generateRoom(int seatRows, int seatColumns) {
        Room room = new Room();
        for (int rowNum = 1; rowNum <= seatRows; rowNum++) {
            Row row = new Row();
            row.setNumber(rowNum);
            for (int column = 1; column <= seatColumns; column++) {
                row.addSeat(generateSeat(column));
            }
            room.addRow(row);
        }
        return room;
    }

    private Seat generateSeat(int column) {
        Seat seat = new Seat();
        seat.setColumn(column);
        return seat;
    }

}
