package com.tunetrove.validator;

import com.tunetrove.annotation.HalfNumber;
import com.tunetrove.annotation.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class HalfNumberValidator implements ConstraintValidator<HalfNumber, Double> {

    @Override
    public void initialize(HalfNumber constraintAnnotation) {}

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Jeśli chcesz traktować wartość null jako nieprawidłową, zwróć false.
        }

        double fraction = value - Math.floor(value);
        return fraction == 0.0 || fraction == 0.5;
    }
}
