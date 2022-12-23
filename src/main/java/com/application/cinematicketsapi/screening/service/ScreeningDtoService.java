package com.application.cinematicketsapi.screening.service;

import com.application.cinematicketsapi.screening.dto.ScreeningDetailedDto;
import com.application.cinematicketsapi.screening.model.Screening;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface exposing {@link ScreeningService} methods that return or take dtos.
 */
public interface ScreeningDtoService {

    /**
     * Retrieves all {@link Screening} entities from repository mapped to detailed dtos.
     *
     * @return the list of detailed dtos representing all found {@code Movie} entities
     */
    List<ScreeningDetailedDto> getAllScreeningDetailedDto();

    /**
     * Retrieves all {@link Screening Screenings} entities that begin at or after the specified lower time boundary and
     * at or before the upper time boundary.
     * <p>
     * The returned {@code Screenings} are sorted by title and screening time.
     *
     * @param lowerTimeBoundary the time after or at which the {@code Screenings} starts
     * @param upperTimeBoundary the time before or at the {@code Screening} starts
     * @return the list of dtos representing the found {@code Screenings} entities sorted by title and screening time
     */
    List<ScreeningDetailedDto> getAllScreeningsBetweenDatesSorted(LocalDateTime lowerTimeBoundary,
                                                                  LocalDateTime upperTimeBoundary);

}
