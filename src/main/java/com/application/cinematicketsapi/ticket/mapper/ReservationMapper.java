package com.application.cinematicketsapi.ticket.mapper;

import com.application.cinematicketsapi.common.exception.DataInconsistencyException;
import com.application.cinematicketsapi.ticket.dto.MoneyDto;
import com.application.cinematicketsapi.ticket.dto.ReservationDto;
import com.application.cinematicketsapi.ticket.model.Reservation;
import com.application.cinematicketsapi.ticket.model.Ticket;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.List;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, uses = TicketMapper.class)
public interface ReservationMapper {

    @Mapping(target = "totalAmountToPay", source = "tickets", qualifiedByName = "ticketToTotalAmount")
    ReservationDto mapReservationToReservationDto(Reservation reservation);

    @Named(value = "ticketToTotalAmount")
    default MoneyDto ticketsToTotalAmount(List<Ticket> tickets) {
        BigDecimal amount = (tickets.stream()
                                    .map(t -> t.getPrice()
                                               .getAmount())
                                    .reduce(BigDecimal::add)
                                    .orElseThrow(() -> new DataInconsistencyException("No prices set for tickets in reservation")));
        long currenciesCount = tickets.stream()
                                      .map(t -> t.getPrice()
                                                 .getCurrency())
                                      .distinct()
                                      .count();
        if (currenciesCount != 1) {
            throw new DataInconsistencyException("Currency not set or multiple currencies set in one reservation");
        }
        return new MoneyDto(amount, tickets.get(0)
                                           .getPrice()
                                           .getCurrency());
    }

}
