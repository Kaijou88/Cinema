package com.project.cinema.security;

import com.project.cinema.exceptions.AuthenticationException;
import com.project.cinema.model.User;
import com.project.cinema.service.UserService;
import com.project.cinema.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private HashUtil hashUtil;

    private final UserService userService;

    public AuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDB = userService.findByEmail(email);
        String hashedPassword = hashUtil.hashPassword(password, userFromDB.getSalt());
        if (userFromDB != null && userFromDB.getPassword().equals(hashedPassword)) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect email or password");
    }

    @Override
    public User register(String email, String password) {
        byte[] salt = hashUtil.getSalt();
        User user = new User();
        String hashedPassword = hashUtil.hashPassword(password, salt);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        user.setEmail(email);
        userService.add(user);
        return user;
    }
}
