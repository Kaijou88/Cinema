package com.project.cinema.model.dto.mapper;

import com.project.cinema.model.User;
import com.project.cinema.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto getUserDto(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setUserId(user.getId());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
