package com.project.cinema.service.impl;

import com.project.cinema.dao.CinemaHallDao;
import com.project.cinema.lib.Inject;
import com.project.cinema.lib.Service;
import com.project.cinema.model.CinemaHall;
import com.project.cinema.service.CinemaHallService;
import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
