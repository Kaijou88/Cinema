package com.project.cinema.dao;

import com.project.cinema.model.Order;
import com.project.cinema.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> findByUser(User user);
}
