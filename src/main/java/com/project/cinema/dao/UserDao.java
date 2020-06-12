package com.project.cinema.dao;

import com.project.cinema.model.User;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);

    User findById(Long userId);
}
