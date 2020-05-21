package com.project.cinema.dao.impl;

import com.project.cinema.dao.MovieSessionDao;
import com.project.cinema.exceptions.DataProcessingException;
import com.project.cinema.lib.Dao;
import com.project.cinema.model.MovieSession;
import com.project.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> query = criteriaBuilder.createQuery(MovieSession.class);
            Root<MovieSession> root = query.from(MovieSession.class);

            Predicate predicateForMovieId = criteriaBuilder.equal(root.get("id"), movieId);
            Predicate predicateForDate =
                    criteriaBuilder.equal(root.get("showTime").as(LocalDate.class), date);
            Predicate finalPredicate = criteriaBuilder.and(predicateForMovieId, predicateForDate);
            CriteriaQuery<MovieSession> criteriaQuery = query.where(finalPredicate);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long movieSessionId = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(movieSessionId);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Movie Session entity", e);

        }
    }
}
