package com.application.cinematicketsapi.ticket.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrencyValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrency {

    String message() default "The currency is not valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
