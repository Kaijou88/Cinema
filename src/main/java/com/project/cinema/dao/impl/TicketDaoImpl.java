package com.project.cinema.dao.impl;

import com.project.cinema.dao.TicketDao;
import com.project.cinema.exceptions.DataProcessingException;
import com.project.cinema.lib.Dao;
import com.project.cinema.model.Ticket;
import com.project.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {

    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Ticket entity", e);
        }
    }
}
