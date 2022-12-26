package com.application.cinematicketsapi.ticket.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

    private String currencyCode;

    @SuppressWarnings("rawtypes")
    @Override
    public void initialize(ValidCurrency constraintAnnotation) {
        currencyCode = "PLN";
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals(currencyCode);
    }

}
