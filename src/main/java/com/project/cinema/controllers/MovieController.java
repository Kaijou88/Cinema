package com.project.cinema.controllers;

import com.project.cinema.config.AppConfig;
import com.project.cinema.model.Movie;
import com.project.cinema.model.dto.MovieRequestDto;
import com.project.cinema.model.dto.MovieResponseDto;
import com.project.cinema.model.dto.mapper.MovieMapper;
import com.project.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private MovieService movieService = context.getBean(MovieService.class);
    @Autowired
    private MovieMapper movieMapper;

    @PostMapping("/add")
    public String addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getMovieTitle());
        movie.setDescription(movieRequestDto.getMovieDescription());
        movieService.add(movie);
        return "Movie was added";
    }

    @GetMapping("/all")
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(movieMapper::getMovieDto)
                .collect(Collectors.toList());
    }
}
