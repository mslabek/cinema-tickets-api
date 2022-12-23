package com.application.cinematicketsapi.cinema.repository;

import com.application.cinematicketsapi.cinema.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to perform CRUD operations on {@link Room} entities in the persistence layer.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
