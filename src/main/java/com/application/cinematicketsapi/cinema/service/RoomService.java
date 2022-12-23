package com.application.cinematicketsapi.cinema.service;

import com.application.cinematicketsapi.cinema.model.Room;
import com.application.cinematicketsapi.cinema.repository.RoomRepository;
import com.application.cinematicketsapi.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

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
        return roomRepository.findById(id).orElseThrow(getResourceNotFoundExceptionSupplier());
    }

    private Supplier<ResourceNotFoundException> getResourceNotFoundExceptionSupplier() {
        return () -> new ResourceNotFoundException("Room was not found in the database.");
    }

}
