package com.application.cinematicketsapi.ticket.mapper;

import com.application.cinematicketsapi.ticket.dto.TicketDto;
import com.application.cinematicketsapi.ticket.model.Ticket;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface TicketMapper {

    @Mapping(target = "column", source = "seat.column")
    @Mapping(target = "row", source = "seat.row.number")
    @Mapping(target = "roomId", source = "seat.row.room.id")
    TicketDto ticketToTicketDto(Ticket ticket);

}
