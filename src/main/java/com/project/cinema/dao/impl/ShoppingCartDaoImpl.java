package com.project.cinema.dao.impl;

import com.project.cinema.dao.ShoppingCartDao;
import com.project.cinema.exceptions.DataProcessingException;
import com.project.cinema.model.ShoppingCart;
import com.project.cinema.model.User;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl extends GenericDaoImpl<ShoppingCart> implements ShoppingCartDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        return super.add(shoppingCart);
    }

    @Override
    public ShoppingCart findById(Long id) {
        return super.findById(id, ShoppingCart.class);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ShoppingCart> query = criteriaBuilder.createQuery(ShoppingCart.class);
            Root<ShoppingCart> root = query.from(ShoppingCart.class);
            root.fetch("tickets", JoinType.LEFT);
            Predicate predicate = criteriaBuilder.equal(root.get("user"), user);
            CriteriaQuery<ShoppingCart> criteriaQuery = query.where(predicate);
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find any user", e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update Shopping Cart entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
