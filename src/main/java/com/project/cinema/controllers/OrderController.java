package com.project.cinema.controllers;

import com.project.cinema.model.Ticket;
import com.project.cinema.model.User;
import com.project.cinema.model.dto.OrderRequestDto;
import com.project.cinema.model.dto.OrderResponseDto;
import com.project.cinema.model.dto.mapper.OrderMapper;
import com.project.cinema.service.OrderService;
import com.project.cinema.service.ShoppingCartService;
import com.project.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, UserService userService,
                           ShoppingCartService shoppingCartService,
                           OrderMapper orderMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public String completeOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        User user = userService.findById(orderRequestDto.getUserId());
        List<Ticket> tickets = shoppingCartService.getByUser(user).getTickets();
        orderService.completeOrder(tickets, user);
        shoppingCartService.clear(shoppingCartService.getByUser(user));
        LOGGER.log(Level.INFO, "Order was completed");
        return "Order was completed";
    }

    @GetMapping("/by-user")
    public List<OrderResponseDto> getOrderHistory(Authentication authentication) {
        String email = authentication.getName();
        return orderService.getOrderHistory(userService.findByEmail(email)).stream()
                .map(orderMapper::getOrderDto)
                .collect(Collectors.toList());
    }
}
