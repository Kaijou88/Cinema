package com.project.cinema.controllers;

import com.project.cinema.model.Movie;
import com.project.cinema.model.dto.MovieRequestDto;
import com.project.cinema.model.dto.MovieResponseDto;
import com.project.cinema.model.dto.mapper.MovieMapper;
import com.project.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public String addMovie(@RequestBody @Valid MovieRequestDto movieRequestDto) {
        Movie movie = movieMapper.createMovie(movieRequestDto);
        movieService.add(movie);
        return "Movie was added";
    }

    @GetMapping
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAll().stream()
                .map(movieMapper::getMovieDto)
                .collect(Collectors.toList());
    }
}
