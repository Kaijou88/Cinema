package com.project.cinema.controllers;

import com.project.cinema.config.AppConfig;
import com.project.cinema.model.User;
import com.project.cinema.model.dto.UserRequestDto;
import com.project.cinema.model.dto.mapper.UserMapper;
import com.project.cinema.security.AuthenticationService;
import com.project.cinema.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private AuthenticationService authenticationService =
            context.getBean(AuthenticationService.class);
    private ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public String registration(@RequestBody UserRequestDto userRequestDto) {
        User user = userMapper.createUser(userRequestDto);
        User registeredUser = authenticationService.register(user.getEmail(), user.getPassword());
        shoppingCartService.registerNewShoppingCart(registeredUser);
        return "User was registered";
    }
}
