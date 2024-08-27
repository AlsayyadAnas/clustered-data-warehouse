package com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CurrencyCodeValidatorClass implements ConstraintValidator<ValidCurrencyCode, String> {


    @Override
    public void initialize(ValidCurrencyCode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String currencyCode, ConstraintValidatorContext constraintValidatorContext) {

        return currencyCode.matches("^[A-Z]{3}$");
    }
}
