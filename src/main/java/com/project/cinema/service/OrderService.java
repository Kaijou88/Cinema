package com.project.cinema.service;

import com.project.cinema.model.Order;
import com.project.cinema.model.Ticket;
import com.project.cinema.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
