package com.project.cinema.dao;

import com.project.cinema.model.ShoppingCart;
import com.project.cinema.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);

    void delete(ShoppingCart shoppingCart);
}
