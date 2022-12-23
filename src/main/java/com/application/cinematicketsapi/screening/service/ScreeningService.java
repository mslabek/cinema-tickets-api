package com.application.cinematicketsapi.screening.service;

import com.application.cinematicketsapi.screening.model.Screening;
import com.application.cinematicketsapi.screening.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service handling operations on {@link Screening} objects.
 */
@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    /**
     * Stores the {@link Screening} in repository.
     *
     * @param screening the screening to be persisted
     */
    public void saveScreening(Screening screening) {
        screeningRepository.save(screening);
    }

}
