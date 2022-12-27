package com.application.cinematicketsapi.screening.mapper;

import com.application.cinematicketsapi.screening.dto.MovieDetailedDto;
import com.application.cinematicketsapi.screening.dto.MovieSimpleDto;
import com.application.cinematicketsapi.screening.model.Movie;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * Mapper used to handle mapping and updating operations on {@link Movie} objects and on related dtos.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy =
        CollectionMappingStrategy.ADDER_PREFERRED, uses = ScreeningSimpleMapper.class)
public interface MovieMapper {

    MovieSimpleDto movieToMovieSimpleDto(Movie movie);

    MovieDetailedDto movieToMovieBigDto(Movie movie);

}
