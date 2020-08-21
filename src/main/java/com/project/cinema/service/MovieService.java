package com.project.cinema.service;

import com.project.cinema.model.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie getById(Long id);
}
