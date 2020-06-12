package com.project.cinema.controllers;

import com.project.cinema.config.AppConfig;
import com.project.cinema.model.dto.UserResponseDto;
import com.project.cinema.model.dto.mapper.UserMapper;
import com.project.cinema.security.AuthenticationService;
import com.project.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private UserService userService = context.getBean(UserService.class);
    private AuthenticationService authenticationService =
            context.getBean(AuthenticationService.class);
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/byemail/{email}/")
    public UserResponseDto getUserByEmail(@PathVariable String email) {
        return userMapper.getUserDto(userService.findByEmail(email));
    }
}
