package com.tunetrove.annotation;

import com.tunetrove.validator.HalfNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HalfNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface HalfNumber {
    String message() default "the rating must be an integer or a half number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
