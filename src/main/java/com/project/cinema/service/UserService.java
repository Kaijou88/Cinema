package com.project.cinema.service;

import com.project.cinema.model.User;

public interface UserService {
    User add(User user);

    User findByEmail(String email);

    User findById(Long userId);
}
