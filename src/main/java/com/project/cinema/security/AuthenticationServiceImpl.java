package com.project.cinema.security;

import com.project.cinema.exceptions.AuthenticationException;
import com.project.cinema.model.Role;
import com.project.cinema.model.User;
import com.project.cinema.service.RoleService;
import com.project.cinema.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     PasswordEncoder passwordEncoder,
                                     RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDB = userService.findByEmail(email);
        if (userFromDB != null && passwordEncoder.matches(password, userFromDB.getPassword())) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect email or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        Role role = roleService.getRoleByName("USER");
        user.setRole(role);
        userService.add(user);
        return user;
    }
}
