package com.application.cinematicketsapi.screening.repository;

import com.application.cinematicketsapi.screening.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);

}
