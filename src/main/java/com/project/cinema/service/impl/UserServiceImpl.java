package com.project.cinema.service.impl;

import com.project.cinema.dao.UserDao;
import com.project.cinema.model.User;
import com.project.cinema.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
    }

    @Override
    public User findById(Long userId) {
        return userDao.findById(userId);
    }
}
