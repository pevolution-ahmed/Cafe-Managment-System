package com.playstation.ms;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceLimitValidation implements ConstraintValidator<PriceLimitaionValidator, Double> {

    private double max;

    @Override
    public void initialize(PriceLimitaionValidator constraint) {
        this.max = Double.parseDouble(constraint.value());
    }

    @Override
    public boolean isValid(Double d, ConstraintValidatorContext constraintValidatorContext) {
   

        return false;
    }
}