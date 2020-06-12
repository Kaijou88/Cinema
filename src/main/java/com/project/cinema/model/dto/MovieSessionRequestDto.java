package com.project.cinema.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class MovieSessionRequestDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime movieSessionShowTime;
    private Long movieId;
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
