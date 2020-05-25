package com.project.cinema.dao;

import com.project.cinema.model.CinemaHall;
import java.util.List;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
