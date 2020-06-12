package com.project.cinema.model.dto.mapper;

import com.project.cinema.model.Order;
import com.project.cinema.model.dto.OrderResponseDto;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    @Autowired
    private TicketMapper ticketMapper;

    public OrderResponseDto getOrderDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getId());
        orderResponseDto.setOrderTime(order.getOrderTime());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setTickets(order.getTickets().stream()
                .map(ticketMapper::getTicketDto)
                .collect(Collectors.toList())
        );
        return orderResponseDto;
    }
}
