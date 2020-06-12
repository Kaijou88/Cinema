package com.project.cinema.controllers;

import com.project.cinema.config.AppConfig;
import com.project.cinema.model.CinemaHall;
import com.project.cinema.model.dto.CinemaHallRequestDto;
import com.project.cinema.model.dto.CinemaHallResponseDto;
import com.project.cinema.model.dto.mapper.CinemaHallMapper;
import com.project.cinema.service.CinemaHallService;
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
@RequestMapping("/cinemahalls")
public class CinemaHallController {
    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
    @Autowired
    private CinemaHallMapper cinemaHallMapper;

    @PostMapping("/add")
    public String addCinemaHall(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = cinemaHallMapper.createCinemaHall(cinemaHallRequestDto);
        cinemaHallService.add(cinemaHall);
        return "Cinema Hall was added";
    }

    @GetMapping("/all")
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHallMapper::getCinemaHallDto)
                .collect(Collectors.toList());
    }
}
