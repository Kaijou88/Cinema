package com.project.cinema.dao.impl;

import com.project.cinema.dao.GenericDao;
import com.project.cinema.exceptions.DataProcessingException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    private final SessionFactory sessionFactory;

    public GenericDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T add(T element) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(element);
            transaction.commit();
            return element;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert "
                    + element.getClass().getName() + " entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    protected T findById(Long id, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
            Root<T> root = query.from(clazz);
            Predicate predicate = criteriaBuilder.equal(root.get("id"), id);
            CriteriaQuery<T> criteriaQuery = query.where(predicate);
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find any " + clazz.getName() + " entity", e);
        }
    }
}
