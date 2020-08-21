package com.project.cinema.dao.impl;

import com.project.cinema.dao.UserDao;
import com.project.cinema.exceptions.DataProcessingException;
import com.project.cinema.model.User;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        return super.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            Predicate predicate = criteriaBuilder.equal(root.get("email"), email);
            CriteriaQuery<User> criteriaQuery = query.where(predicate);
            return session.createQuery(criteriaQuery).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find any user", e);
        }
    }

    @Override
    public User findById(Long userId) {
        return super.findById(userId, User.class);
    }
}
