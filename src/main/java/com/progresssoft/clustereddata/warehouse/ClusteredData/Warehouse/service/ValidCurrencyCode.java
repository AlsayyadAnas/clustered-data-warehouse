package com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.service;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CurrencyCodeValidatorClass.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE,ElementType.METHOD,ElementType.ANNOTATION_TYPE,})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrencyCode {

    String message() default "Invalid currency code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
