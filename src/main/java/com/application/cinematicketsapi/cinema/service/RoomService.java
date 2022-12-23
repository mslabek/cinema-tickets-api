package com.application.cinematicketsapi.cinema.service;

import com.application.cinematicketsapi.cinema.model.Room;
import com.application.cinematicketsapi.cinema.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service handling operations on {@link Room} objects.
 */
@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public Room getRoom(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found in the database."));
    }

}
