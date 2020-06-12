package com.project.cinema.model.dto.mapper;

import com.project.cinema.model.Movie;
import com.project.cinema.model.dto.MovieRequestDto;
import com.project.cinema.model.dto.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieResponseDto getMovieDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setMovieId(movie.getId());
        movieResponseDto.setMovieTitle(movie.getTitle());
        movieResponseDto.setMovieDescription(movie.getDescription());
        return movieResponseDto;
    }

    public Movie createMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getMovieTitle());
        movie.setDescription(movieRequestDto.getMovieDescription());
        return movie;
    }
}
