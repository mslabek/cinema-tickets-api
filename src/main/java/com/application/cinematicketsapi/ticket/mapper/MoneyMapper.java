package com.application.cinematicketsapi.ticket.mapper;

import com.application.cinematicketsapi.ticket.dto.MoneyDto;
import com.application.cinematicketsapi.ticket.form.PaymentForm;
import com.application.cinematicketsapi.ticket.model.Money;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MoneyMapper {

    Money mapPaymentFormToMoney(PaymentForm form);

    MoneyDto moneyToMoneyDto(Money money);

}
