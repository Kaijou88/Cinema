package com.project.cinema.controllers;

import com.project.cinema.model.dto.UserResponseDto;
import com.project.cinema.model.dto.mapper.UserMapper;
import com.project.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/by-email/{email}/")
    public UserResponseDto getUserByEmail(@PathVariable String email) {
        return userMapper.getUserDto(userService.findByEmail(email));
    }
}
