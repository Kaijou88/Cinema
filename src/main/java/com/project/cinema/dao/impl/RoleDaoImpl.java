package com.project.cinema.dao.impl;

import com.project.cinema.dao.RoleDao;
import com.project.cinema.exceptions.DataProcessingException;
import com.project.cinema.model.Role;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role add(Role role) {
        return super.add(role);
    }

    @Override
    public Role findById(Long id) {
        return super.findById(id, Role.class);
    }

    @Override
    public Role getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Role.RoleName name = Role.RoleName.valueOf(roleName);
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Role> query = criteriaBuilder.createQuery(Role.class);
            Root<Role> root = query.from(Role.class);
            Predicate predicate = criteriaBuilder.equal(root.get("roleName"), name);
            CriteriaQuery<Role> criteriaQuery = query.where(predicate);
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find any role", e);
        }
    }
}
