package com.project.cinema.controllers;

import com.project.cinema.model.CinemaHall;
import com.project.cinema.model.dto.CinemaHallRequestDto;
import com.project.cinema.model.dto.CinemaHallResponseDto;
import com.project.cinema.model.dto.mapper.CinemaHallMapper;
import com.project.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final CinemaHallMapper cinemaHallMapper;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @PostMapping
    public String addCinemaHall(@RequestBody @Valid CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = cinemaHallMapper.createCinemaHall(cinemaHallRequestDto);
        cinemaHallService.add(cinemaHall);
        return "Cinema Hall was added";
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHallMapper::getCinemaHallDto)
                .collect(Collectors.toList());
    }
}
