package com.project.cinema.security;

import com.project.cinema.exceptions.AuthenticationException;
import com.project.cinema.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
