package com.project.cinema.model.dto;

import java.time.LocalDateTime;

public class TicketResponseDto {
    private Long ticketId;
    private Long movieSessionId;
    private LocalDateTime movieSessionShowTime;
    private String userEmail;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }

    public LocalDateTime getMovieSessionShowTime() {
        return movieSessionShowTime;
    }

    public void setMovieSessionShowTime(LocalDateTime movieSessionShowTime) {
        this.movieSessionShowTime = movieSessionShowTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
