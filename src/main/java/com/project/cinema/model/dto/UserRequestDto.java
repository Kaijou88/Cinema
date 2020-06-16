package com.project.cinema.model.dto;

import com.project.cinema.annotations.EmailConstraint;
import com.project.cinema.annotations.PasswordConstraint;

@PasswordConstraint(
        field = "userPassword",
        fieldMatch = "repeatUserPassword"
)
public class UserRequestDto {
    @EmailConstraint
    private String userEmail;
    private String userPassword;
    private String repeatUserPassword;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRepeatUserPassword() {
        return repeatUserPassword;
    }

    public void setRepeatUserPassword(String repeatUserPassword) {
        this.repeatUserPassword = repeatUserPassword;
    }
}
