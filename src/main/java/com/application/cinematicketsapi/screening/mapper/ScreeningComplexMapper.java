package com.application.cinematicketsapi.screening.mapper;

import com.application.cinematicketsapi.screening.dto.ScreeningFullDto;
import com.application.cinematicketsapi.screening.model.Screening;

public interface ScreeningComplexMapper {

    ScreeningFullDto screeningToFullDto(Screening screening);

}
