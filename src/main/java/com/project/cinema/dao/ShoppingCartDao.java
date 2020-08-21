package com.project.cinema.dao;

import com.project.cinema.model.ShoppingCart;
import com.project.cinema.model.User;

public interface ShoppingCartDao extends GenericDao<ShoppingCart> {
    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
