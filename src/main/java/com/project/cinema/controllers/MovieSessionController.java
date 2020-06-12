package com.project.cinema.controllers;

import com.project.cinema.config.AppConfig;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {
    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private MovieService movieService = context.getBean(MovieService.class);
    private CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
    private MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
    @Autowired
    private MovieSessionMapper movieSessionMapper;

    @PostMapping("/add")
    public String addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
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
