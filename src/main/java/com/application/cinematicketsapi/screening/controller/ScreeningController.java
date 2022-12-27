package com.application.cinematicketsapi.screening.controller;

import com.application.cinematicketsapi.cinema.model.Seat;
import com.application.cinematicketsapi.screening.dto.ScreeningDetailedDto;
import com.application.cinematicketsapi.screening.dto.ScreeningFullDto;
import com.application.cinematicketsapi.screening.model.Screening;
import com.application.cinematicketsapi.screening.service.ScreeningDtoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Rest controller handling requests referring to {@link Screening screenings}.
 */
@Tag(name = "Screenings", description = "Operations referring to screenings")
@RestController
@RequestMapping("/screenings")
@RequiredArgsConstructor
@Validated
public class ScreeningController {

    private final ScreeningDtoService screeningService;

    /**
     * Retrieves all {@link Screening Screenings} from repository.
     *
     * @return the list of dtos representing the {@code Screening} entities
     */
    @GetMapping
    public List<ScreeningDetailedDto> getAllScreenings() {
        return screeningService.getAllScreeningDetailedDto();
    }

    /**
     * Retrieves {@link Screening Screenings} that begin at or after the specified lower time boundary and at or before
     * the upper time boundary. This can be useful when client wants to know what {@code Screenings} are available in a
     * specific time frame.
     * <p>
     * The returned {@code Screenings} are sorted by title and screening time.
     *
     * @param lowerTimeBoundary the time after or at which the {@code Screenings} starts
     * @param upperTimeBoundary the time before or at the {@code Screening} starts
     * @return the list of dtos representing the found {@code Screenings} entities sorted by title and screening time
     */
    @GetMapping(params = {"begins-after", "begins-before"})
    @Operation(description = "The screenings are sorted by title of the movie first, then by beginning of the " + "screening", summary = "Retrieves all screenings starting between two dates")
    @Parameter(name = "begins-after", description = "the time after which the screening should be starting (lower " +
            "boundary)", example = "2022-12-30T11:05:00")
    @Parameter(name = "begins-before", description = "the time before which the screening should be starting (upper " +
            "boundary)", example = "2022-12-30T16:00:00")
    @ApiResponse(responseCode = "200", description = "Screenings retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public List<ScreeningDetailedDto> getScreeningsFilteredSorted(
            @RequestParam(value = "begins-after") LocalDateTime lowerTimeBoundary,
            @RequestParam(value = "begins-before") LocalDateTime upperTimeBoundary) {
        return screeningService.getAllScreeningsBetweenDatesSorted(lowerTimeBoundary, upperTimeBoundary);
    }

    /**
     * Retrieves a {@link Screening} from repository mapped to a very detailed dto. This dto contains information about
     * all {@link Seat seats} that are in a room where the {code screening} takes place and the status of these
     * {@code seats} for the specified {@code screening}.
     * <p>
     * This can be used for creating a view where the client can select seats for reservation.
     *
     * @param id the id of the searched {@code Screening}
     * @return the dto representing the found {@code Screening} containing seat status
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retrieves detailed information about a screening")
    @Parameter(name = "id", description = "the id of the searched screening", example = "1")
    @ApiResponse(responseCode = "200", description = "Screenings retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Request validation error", content = @Content)
    @ApiResponse(responseCode = "404", description = "Screening with specified id not found", content = @Content)
    @ApiResponse(responseCode = "5xx", description = "Unexpected error", content = @Content)
    public ScreeningFullDto getScreeningWithSeatStatus(
            @PathVariable @Min(value = 1, message = "Screening id cannot be smaller than 1.") Long id) {
        return screeningService.getScreeningWithSeatStatus(id);
    }

}
