package com.project.cinema.annotations;

import com.project.cinema.model.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, UserRequestDto> {
    private String userPassword;
    private String repeatUserPassword;

    public void initialize(PasswordConstraint constraintAnnotation) {
        this.userPassword = constraintAnnotation.field();
        this.repeatUserPassword = constraintAnnotation.fieldMatch();
    }

    public boolean isValid(UserRequestDto userRequestDto,
                          ConstraintValidatorContext context) {

        String userPasswordValue = (String) new BeanWrapperImpl(userRequestDto)
                .getPropertyValue(userPassword);
        String repeatUserPasswordValue = (String) new BeanWrapperImpl(userRequestDto)
                .getPropertyValue(repeatUserPassword);

        return userPasswordValue != null
              && userPasswordValue.equals(repeatUserPasswordValue)
              && userPasswordValue.length() >= 3;
    }
}
