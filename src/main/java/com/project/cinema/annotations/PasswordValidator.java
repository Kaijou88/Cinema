package com.project.cinema.annotations;

import com.project.cinema.model.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, UserRequestDto> {
    public boolean isValid(UserRequestDto userRequestDto,
                          ConstraintValidatorContext context) {
        String userPasswordValue = userRequestDto.getUserPassword();
        String repeatUserPasswordValue = userRequestDto.getRepeatUserPassword();
        return userPasswordValue != null
              && userPasswordValue.equals(repeatUserPasswordValue)
              && userPasswordValue.length() >= 3;
    }
}
