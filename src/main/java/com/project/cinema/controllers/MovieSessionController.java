package com.project.cinema.controllers;

import com.project.cinema.model.MovieSession;
import com.project.cinema.model.dto.MovieSessionRequestDto;
import com.project.cinema.model.dto.MovieSessionResponseDto;
import com.project.cinema.model.dto.mapper.MovieSessionMapper;
import com.project.cinema.service.CinemaHallService;
import com.project.cinema.service.MovieService;
import com.project.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieService movieService, CinemaHallService cinemaHallService,
                                  MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping
    public String addMovieSession(@RequestBody @Valid MovieSessionRequestDto
                                              movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getAll().stream()
                .filter(m -> m.getId().equals(movieSessionRequestDto.getMovieId()))
                .findFirst()
                .get()
        );
        movieSession.setShowTime(movieSessionRequestDto.getMovieSessionShowTime());
        movieSession.setCinemaHall(cinemaHallService.getAll().stream()
                .filter(c -> c.getId().equals(movieSessionRequestDto.getCinemaHallId()))
                .findFirst()
                .get()
        );
        movieSessionService.add(movieSession);
        return "Movie session was added";
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableMovieSession(Long movieId,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(movieSessionMapper::getMovieSessionDto)
                .collect(Collectors.toList());
    }
}
