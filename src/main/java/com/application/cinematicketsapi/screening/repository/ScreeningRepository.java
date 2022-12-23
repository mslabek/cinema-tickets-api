package com.application.cinematicketsapi.screening.repository;

import com.application.cinematicketsapi.screening.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository used to perform CRUD operations on {@link Screening} entities in the persistence layer.
 */
@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
