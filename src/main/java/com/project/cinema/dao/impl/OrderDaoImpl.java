package com.project.cinema.dao.impl;

import com.project.cinema.dao.OrderDao;
import com.project.cinema.exceptions.DataProcessingException;
import com.project.cinema.model.Order;
import com.project.cinema.model.User;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        return super.add(order);
    }

    @Override
    public Order findById(Long id) {
        return super.findById(id, Order.class);
    }

    @Override
    public List<Order> findByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            root.fetch("tickets", JoinType.LEFT);
            Predicate predicate = criteriaBuilder.equal(root.get("user"), user);
            CriteriaQuery<Order> criteriaQuery = query.where(predicate);
            return session.createQuery(criteriaQuery).list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find any orders", e);
        }
    }
}
