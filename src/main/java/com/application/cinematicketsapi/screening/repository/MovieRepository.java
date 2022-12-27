package com.application.cinematicketsapi.screening.repository;

import com.application.cinematicketsapi.screening.model.Movie;
import com.application.cinematicketsapi.screening.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Repository used to perform CRUD operations on {@link Movie} entities in the persistence layer.
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);

    /**
     * Retrieves a Movie with specified {@code id}. The entity contains all associated {@link Screening Screenings}.
     *
     * @param id the id of the movie
     * @return the found {@code Movie} entity containing {@code Screenings}
     */
    @Query("SELECT m FROM Movie m " +
            "JOIN FETCH m.screenings " +
            "WHERE m.id = :id")
    Optional<Movie> findByIdWithScreenings(Long id);

}
