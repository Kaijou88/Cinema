package com.project.cinema.dao.impl;

import com.project.cinema.dao.MovieSessionDao;
import com.project.cinema.exceptions.DataProcessingException;
import com.project.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl extends GenericDaoImpl<MovieSession> implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> query = criteriaBuilder.createQuery(MovieSession.class);
            Root<MovieSession> root = query.from(MovieSession.class);

            Predicate predicateForMovieId = criteriaBuilder.equal(root.get("id"), movieId);
            Predicate predicateForDate =
                    criteriaBuilder.equal(root.get("showTime").as(LocalDate.class), date);
            Predicate finalPredicate = criteriaBuilder.and(predicateForMovieId, predicateForDate);
            CriteriaQuery<MovieSession> criteriaQuery = query.where(finalPredicate);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find any available sessions", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        return super.add(movieSession);
    }

    @Override
    public MovieSession findById(Long movieSessionId) {
        return super.findById(movieSessionId, MovieSession.class);
    }
}
