package com.fral.extreme.s4.domain.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * TO BE IMPLEMENTED
 */
//public class SprintHibernateDao extends HibernateDaoSupport implements S4SystemDao {
//
//
//    @Autowired
//    public SpringHibernateDao(SessionFactory sessionFactory) {
//        super.setSessionFactory(sessionFactory);
//    }
//
//    @Transactional
//    @Override
//    public <T> T persist(T entity) {
//        getHibernateTemplate().saveOrUpdate(entity);
//        return entity;
//    }
//
//    @Transactional
//    @Override
//    public <T> Collection<T> persist(T[] entities) {
//        for (Object entity : entities) {
//            persist(entity);
//        }
//        return new ArrayList<T>(Arrays.asList(entities));
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public <T> Collection<T> find(Class<T> entityClass) {
//        final Collection<T> entities = getHibernateTemplate().loadAll(entityClass);
//        return entities;
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public <T> T load(Class<T> entityClass, Serializable id) {
//        final T entity = getHibernateTemplate().load(entityClass, id);
//        return entity;
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public <T> Collection<T> find(String hql) {
//        final Collection<T> entities = (Collection<T>) getHibernateTemplate().find(hql);
//        return entities;
//    }
//
//    @Override
//    public boolean delete(Object entity) {
//        return true;
//    }
//
//    @Override
//    public boolean deleteAll() {
//        return true;
//    }
//}
