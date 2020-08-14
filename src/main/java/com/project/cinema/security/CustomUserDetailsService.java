package com.project.cinema.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.project.cinema.model.User;
import com.project.cinema.service.UserService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        UserBuilder builder;
        if (user != null) {
            builder = withUsername(email);
            builder.password(user.getPassword());
            builder.roles(user.getRole().getRoleName().name());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}
