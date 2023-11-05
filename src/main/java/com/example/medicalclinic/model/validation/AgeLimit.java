package com.example.medicalclinic.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeLimitValidator.class)
@Documented
public @interface AgeLimit {
    String message() default "Age must be higher than 18 years and not in the future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}