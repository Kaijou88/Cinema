package com.project.cinema.model.dto.mapper;

import com.project.cinema.model.CinemaHall;
import com.project.cinema.model.dto.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {

    public CinemaHallResponseDto getCinemaHallDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setCinemaHallId(cinemaHall.getId());
        cinemaHallResponseDto.setCinemaHallCapacity(cinemaHall.getCapacity());
        cinemaHallResponseDto.setCinemaHallDescription(cinemaHall.getDescription());
        return cinemaHallResponseDto;
    }
}
