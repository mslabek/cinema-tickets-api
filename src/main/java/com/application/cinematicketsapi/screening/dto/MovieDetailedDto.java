package com.application.cinematicketsapi.screening.dto;

import java.util.List;

public record MovieDetailedDto(Long id, String title, List<ScreeningSimpleDto> screenings) {
}
