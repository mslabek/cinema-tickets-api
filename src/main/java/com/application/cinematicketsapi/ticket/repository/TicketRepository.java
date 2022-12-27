package com.application.cinematicketsapi.ticket.repository;

import com.application.cinematicketsapi.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to perform CRUD operations on {@link Ticket} entities in the persistence layer.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
