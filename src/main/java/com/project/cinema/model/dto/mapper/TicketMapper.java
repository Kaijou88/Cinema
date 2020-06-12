package com.project.cinema.model.dto.mapper;

import com.project.cinema.model.Ticket;
import com.project.cinema.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketResponseDto getTicketDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setTicketId(ticket.getId());
        ticketResponseDto.setUserEmail(ticket.getUser().getEmail());
        ticketResponseDto.setMovieSessionShowTime(ticket.getMovieSession().getShowTime());
        return ticketResponseDto;
    }
}
