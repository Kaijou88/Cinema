package com.project.cinema;

import com.project.cinema.lib.Injector;
import com.project.cinema.model.Movie;
import com.project.cinema.service.MovieService;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.project.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movieService.add(movie);
        movie.setTitle("Mad Max");
        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
