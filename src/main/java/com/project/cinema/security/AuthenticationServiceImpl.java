package com.project.cinema.security;

import com.project.cinema.exceptions.AuthenticationException;
import com.project.cinema.model.User;
import com.project.cinema.service.UserService;
import com.project.cinema.util.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;

    public AuthenticationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDB = userService.findByEmail(email);
        String hashedPassword = HashUtil.hashPassword(password, userFromDB.getSalt());
        if (userFromDB != null && userFromDB.getPassword().equals(hashedPassword)) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect email or password");
    }

    @Override
    public User register(String email, String password) {
        byte[] salt = HashUtil.getSalt();
        User user = new User();
        String hashedPassword = HashUtil.hashPassword(password, salt);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        user.setEmail(email);
        userService.add(user);
        return user;
    }
}
