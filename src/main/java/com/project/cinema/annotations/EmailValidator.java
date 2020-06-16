package com.project.cinema.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    private static final String VALID_EMAIL_REGEX
                                = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null
                && value.matches(VALID_EMAIL_REGEX);
    }
}
