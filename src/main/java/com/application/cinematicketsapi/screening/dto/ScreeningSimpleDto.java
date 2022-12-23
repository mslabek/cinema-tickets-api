package com.application.cinematicketsapi.screening.dto;

import java.time.LocalDateTime;

public record ScreeningSimpleDto(Long id, Long roomId, LocalDateTime beginning, LocalDateTime ending) {
}
