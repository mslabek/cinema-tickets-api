package com.application.cinematicketsapi.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {

    private Set<String> validValues;

    @SuppressWarnings("rawtypes")
    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        Class<? extends Enum> selectedEnum = constraintAnnotation.targetClassType();
        validValues = Arrays.stream(selectedEnum.getEnumConstants())
                            .map(Enum::name)
                            .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validValues.contains(value);
    }

}
