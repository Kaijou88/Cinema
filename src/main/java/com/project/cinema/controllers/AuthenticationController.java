package com.project.cinema.controllers;

import com.project.cinema.model.User;
import com.project.cinema.model.dto.UserRequestDto;
import com.project.cinema.model.dto.mapper.UserMapper;
import com.project.cinema.security.AuthenticationService;
import com.project.cinema.service.ShoppingCartService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ShoppingCartService shoppingCartService;
    private final UserMapper userMapper;

    public AuthenticationController(AuthenticationService authenticationService,
                                    ShoppingCartService shoppingCartService,
                                    UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.shoppingCartService = shoppingCartService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public String registration(@RequestBody @Valid UserRequestDto userRequestDto) {
        User user = userMapper.createUser(userRequestDto);
        User registeredUser = authenticationService.register(user.getEmail(), user.getPassword());
        shoppingCartService.registerNewShoppingCart(registeredUser);
        return "User was registered";
    }
}
