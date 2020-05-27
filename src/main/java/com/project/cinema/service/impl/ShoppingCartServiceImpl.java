package com.project.cinema.service.impl;

import com.project.cinema.dao.ShoppingCartDao;
import com.project.cinema.dao.TicketDao;
import com.project.cinema.lib.Inject;
import com.project.cinema.lib.Service;
import com.project.cinema.model.MovieSession;
import com.project.cinema.model.ShoppingCart;
import com.project.cinema.model.Ticket;
import com.project.cinema.model.User;
import com.project.cinema.service.ShoppingCartService;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private TicketDao ticketDao;
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setMovieSession(movieSession);
        ticket.setUser(user);
        ticket = ticketDao.add(ticket);
        ShoppingCart shoppingCart = shoppingCartDao.getByUser(user);
        shoppingCart.setTickets(List.of(ticket));
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }
}
