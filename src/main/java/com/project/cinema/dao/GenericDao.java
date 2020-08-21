package com.project.cinema.dao;

public interface GenericDao<T> {
    T add(T element);

    T findById(Long id);
}
