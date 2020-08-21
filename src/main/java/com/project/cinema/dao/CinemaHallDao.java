package com.project.cinema.dao;

import com.project.cinema.model.CinemaHall;
import java.util.List;

public interface CinemaHallDao extends GenericDao<CinemaHall> {
    List<CinemaHall> getAll();
}
