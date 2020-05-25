package com.project.cinema.security;

import com.project.cinema.exceptions.AuthenticationException;
import com.project.cinema.lib.Inject;
import com.project.cinema.lib.Service;
import com.project.cinema.model.User;
import com.project.cinema.service.UserService;
import com.project.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDB = userService.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Incorrect email or password"));

        String hashedPassword = HashUtil.hashPassword(password, userFromDB.getSalt());
        if (userFromDB.getPassword().equals(hashedPassword)) {
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
