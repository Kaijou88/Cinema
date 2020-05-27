package com.project.cinema.service;

import com.project.cinema.model.MovieSession;
import com.project.cinema.model.ShoppingCart;
import com.project.cinema.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);
}
