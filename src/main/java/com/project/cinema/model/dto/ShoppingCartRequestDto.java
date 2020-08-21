package com.project.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class ShoppingCartRequestDto {
    @NotNull(message = "Movie session id should not be null")
    private Long movieSessionId;

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }
}
