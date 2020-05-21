package com.project.cinema.service.impl;

import com.project.cinema.dao.MovieSessionDao;
import com.project.cinema.lib.Inject;
import com.project.cinema.lib.Service;
import com.project.cinema.model.MovieSession;
import com.project.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }
}
