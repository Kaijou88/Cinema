package com.project.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class OrderRequestDto {
    @NotNull(message = "User id should not be null")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
