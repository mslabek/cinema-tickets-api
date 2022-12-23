package com.application.cinematicketsapi.screening.mapper;

import com.application.cinematicketsapi.screening.dto.ScreeningSimpleDto;
import com.application.cinematicketsapi.screening.model.Screening;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper used to handle mapping and updating operations on {@link Screening} objects and on related dtos.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy =
        CollectionMappingStrategy.ADDER_PREFERRED, uses = MovieMapper.class)
public interface ScreeningMapper {

    @Mapping(target = "roomId", source = "room.id")
    ScreeningSimpleDto screeningToSimpleDto(Screening screening);

}
