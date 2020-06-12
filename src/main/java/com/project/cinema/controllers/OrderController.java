package com.project.cinema.controllers;

import com.project.cinema.config.AppConfig;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private OrderService orderService = context.getBean(OrderService.class);
    private UserService userService = context.getBean(UserService.class);
    private ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
    @Autowired
    private OrderMapper orderMapper;

    @PostMapping("/complete")
    public String completeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        User user = userService.findById(orderRequestDto.getUserId());
        List<Ticket> tickets = shoppingCartService.getByUser(user).getTickets();
        orderService.completeOrder(tickets, user);
        shoppingCartService.clear(shoppingCartService.getByUser(user));
        return "Order was completed";
    }

    @GetMapping("/byuser")
    public List<OrderResponseDto> getOrderHistory(Long userId) {
        return orderService.getOrderHistory(userService.findById(userId)).stream()
                .map(orderMapper::getOrderDto)
                .collect(Collectors.toList());
    }
}
