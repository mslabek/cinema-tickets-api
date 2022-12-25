package com.application.cinematicketsapi.screening.service;

import com.application.cinematicketsapi.common.exception.ResourceNotFoundException;
import com.application.cinematicketsapi.screening.dto.ScreeningDetailedDto;
import com.application.cinematicketsapi.screening.dto.ScreeningFullDto;
import com.application.cinematicketsapi.screening.mapper.ScreeningComplexMapper;
import com.application.cinematicketsapi.screening.mapper.ScreeningSimpleMapper;
import com.application.cinematicketsapi.screening.model.Movie;
import com.application.cinematicketsapi.screening.model.Screening;
import com.application.cinematicketsapi.screening.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

/**
 * Service handling operations on {@link Screening} objects.
 */
@Service
@RequiredArgsConstructor
public class ScreeningService implements ScreeningDtoService {

    private final ScreeningRepository screeningRepository;
    private final ScreeningSimpleMapper screeningSimpleMapper;
    private final ScreeningComplexMapper screeningComplexMapper;

    /**
     * Stores the {@link Screening} in repository.
     *
     * @param screening the screening to be persisted
     */
    public void saveScreening(Screening screening) {
        screeningRepository.save(screening);
    }

    /**
     * Retrieves all {@link Screening Screenings}from repository containing all associated {@link Movie Movies}.
     *
     * @return the list of {@code Screening} entities containing associated {@code Movies}
     */
    public List<Screening> getAllScreeningsWithMovies() {
        return screeningRepository.findAllWithMovies();
    }

    @Override
    public List<ScreeningDetailedDto> getAllScreeningDetailedDto() {
        return getAllScreeningsWithMovies().stream()
                                           .map(screeningSimpleMapper::screeningToDetailedDto)
                                           .toList();
    }

    @Override
    public List<ScreeningDetailedDto> getAllScreeningsBetweenDatesSorted(LocalDateTime lowerTimeBoundary,
                                                                         LocalDateTime upperTimeBoundary) {
        return screeningRepository.findAllBeginningBetweenDatesSortedByTitleAndTime(lowerTimeBoundary, upperTimeBoundary)
                                  .stream()
                                  .map(screeningSimpleMapper::screeningToDetailedDto)
                                  .toList();
    }

    public ScreeningFullDto getScreeningWithSeatStatus(Long id) {
        Screening screening =
                screeningRepository.findScreeningWithMovieSeatsAndAllSeatTickets(id)
                                   .orElseThrow(getResourceNotFoundExceptionSupplier());
        filterTicketsExpiredAndFromOtherScreenings(screening);
        return screeningComplexMapper.screeningToFullDto(screening);
    }

    private void filterTicketsExpiredAndFromOtherScreenings(Screening screening) {
        // TODO
    }

    private static Supplier<ResourceNotFoundException> getResourceNotFoundExceptionSupplier() {
        return () -> new ResourceNotFoundException("Screening not found in the database");
    }

}
