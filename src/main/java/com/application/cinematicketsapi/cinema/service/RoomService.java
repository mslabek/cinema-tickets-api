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

    /**
     * Stores a {@link Room} in the repository.
     *
     * @param room the room to be persisted
     */
    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    /**
     * Retrieves a {@link Room} entity from repository.
     *
     * @param id the id of the searched {@code Room}
     * @return the found {@code Room} entity
     */
    public Room getRoom(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found in the database."));
    }

}
