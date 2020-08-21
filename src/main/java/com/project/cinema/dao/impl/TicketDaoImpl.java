package com.project.cinema.dao.impl;

import com.project.cinema.dao.TicketDao;
import com.project.cinema.model.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends GenericDaoImpl<Ticket> implements TicketDao {
    @Autowired
    public TicketDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Ticket add(Ticket ticket) {
        return super.add(ticket);
    }

    @Override
    public Ticket findById(Long id) {
        return super.findById(id, Ticket.class);
    }
}
