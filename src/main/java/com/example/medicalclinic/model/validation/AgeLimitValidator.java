package com.example.medicalclinic.model.validation;

import com.example.medicalclinic.model.validation.AgeLimit;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeLimitValidator implements ConstraintValidator<AgeLimit, LocalDate> {

    @Override
    public void initialize(AgeLimit constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateOfBirth, now);

        // Check if age is greater than or equal to 18 and date is not in the future
        return period.getYears() >= 18 && !dateOfBirth.isAfter(now);
    }
}