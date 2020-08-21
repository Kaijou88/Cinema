package com.project.cinema.dao;

import com.project.cinema.model.User;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByEmail(String email);
}
