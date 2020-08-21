package com.project.cinema.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class MovieSessionRequestDto {
    @NotNull(message = "Show time should not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime movieSessionShowTime;
    @NotNull(message = "Movie id should not be null")
    private Long movieId;
    @NotNull(message = "Cinema Hall id should not be null")
    private Long cinemaHallId;

    public LocalDateTime getMovieSessionShowTime() {
        return movieSessionShowTime;
    }

    public void setMovieSessionShowTime(LocalDateTime movieSessionShowTime) {
        this.movieSessionShowTime = movieSessionShowTime;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }
}
