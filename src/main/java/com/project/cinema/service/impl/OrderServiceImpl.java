package com.project.cinema.service.impl;

import com.project.cinema.dao.OrderDao;
import com.project.cinema.lib.Inject;
import com.project.cinema.lib.Service;
import com.project.cinema.model.Order;
import com.project.cinema.model.Ticket;
import com.project.cinema.model.User;
import com.project.cinema.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setOrderTime(LocalDateTime.now());
        order.setTickets(tickets);
        order.setUser(user);
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.findByUser(user);
    }
}
