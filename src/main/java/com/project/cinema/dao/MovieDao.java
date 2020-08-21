package com.project.cinema.dao;

import com.project.cinema.model.Movie;
import java.util.List;

public interface MovieDao extends GenericDao<Movie> {
    List<Movie> getAll();
}
