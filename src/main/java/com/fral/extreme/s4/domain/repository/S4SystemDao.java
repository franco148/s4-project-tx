package com.fral.extreme.s4.domain.repository;

import java.io.Serializable;
import java.util.Collection;

public interface S4SystemDao {

    void persist(Object entity);
    void persist(Object[] entities);
    <T> Collection<T> find(Class<T> entityClass);
    <T> T load(Class<T> entityClass, Serializable id);
    <T> Collection<T> find(String customQuery);
    void delete(Object entity);
    void deleteAll();
}
