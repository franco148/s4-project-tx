package com.fral.extreme.s4.domain.repository;

import java.io.Serializable;
import java.util.Collection;

public interface S4SystemDao {

    <T> T persist(T entity);
    <T> T persist(T[] entities);
    <T> Collection<T> find(Class<T> entityClass);
    <T> T load(Class<T> entityClass, Serializable id);
    <T> Collection<T> find(String customQuery);
    boolean delete(Object entity);
    boolean deleteAll();
}
