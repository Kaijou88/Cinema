package com.project.cinema.model.dto.mapper;

import com.project.cinema.model.ShoppingCart;
import com.project.cinema.model.dto.ShoppingCartResponseDto;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    @Autowired
    private TicketMapper ticketMapper;

    public ShoppingCartResponseDto getShoppingCartDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setShoppingCartId(shoppingCart.getId());
        shoppingCartResponseDto.setUserId(shoppingCart.getUser().getId());
        shoppingCartResponseDto.setTickets(shoppingCart.getTickets().stream()
                .map(ticketMapper::getTicketDto)
                .collect(Collectors.toList())
        );
        return shoppingCartResponseDto;
    }
}
