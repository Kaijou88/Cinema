package com.project.cinema.security;

import com.project.cinema.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
