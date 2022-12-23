package com.application.cinematicketsapi.screening.repository;

import com.application.cinematicketsapi.screening.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);

    @Query("SELECT m FROM Movie m " +
            "JOIN FETCH m.screenings " +
            "WHERE m.id = :id")
    Optional<Movie> findByIdWithScreenings(Long id);

}
