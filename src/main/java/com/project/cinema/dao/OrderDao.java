package com.project.cinema.dao;

import com.project.cinema.model.Order;
import com.project.cinema.model.User;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> findByUser(User user);
}
