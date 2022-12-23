package com.application.cinematicketsapi.screening.repository;

import com.application.cinematicketsapi.screening.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository used to perform CRUD operations on {@link Screening} entities in the persistence layer.
 */
@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    @Query("SELECT s FROM Screening s " +
            "JOIN FETCH s.movie m")
    List<Screening> findAllWithMovies();

    @Query("SELECT s FROM Screening s " +
            "JOIN FETCH s.movie m " +
            "WHERE s.beginning > :lowerTime " +
            "AND s.beginning < :upperTime " +
            "ORDER BY m.title, s.beginning")
    List<Screening> findAllBeginningBetweenDatesSortedByTitleAndTime(
            @Param("lowerTime") LocalDateTime lowerTimeBoundary,
            @Param("upperTime") LocalDateTime upperTimeBoundary);

}
