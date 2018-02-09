package com.fral.extreme.s4.services;

import com.fral.extreme.s4.common.dto.DtoBase;
import com.fral.extreme.s4.domain.model.BaseEntity;
import com.fral.extreme.s4.domain.repository.S4SystemDao;
import com.fral.extreme.s4.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class S4SystemService {

    private S4SystemDao systemDao;

    @Autowired
    public S4SystemService(S4SystemDao systemDao) {
        this.systemDao = systemDao;
    }

    public <R extends DtoBase, T extends BaseEntity> R find(Class<R> entityResponse, Class<T> entityParameter, Serializable entityId) throws EntityNotFoundException {
        BaseEntity retrievedEntity = systemDao.load(entityParameter, entityId);

        if (retrievedEntity == null) {
            throw new EntityNotFoundException();
        }

        R response = null;
        try {
            response = entityResponse.newInstance();
            response.set(retrievedEntity);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return response;
    }

    public <R extends DtoBase, T extends BaseEntity> Set<R> getAll(Class<R> entityResponse, Class<T> entityParameter) {
        Set<R> resultCollection = new HashSet<>();

        Collection<T> retrievedEntities = systemDao.find(entityParameter);

        for (T entity : retrievedEntities) {
            try {
                R dto = entityResponse.newInstance();
                dto.set(entity);

                resultCollection.add(dto);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return resultCollection;
    }

    public <R extends DtoBase, T extends BaseEntity> R save(Class<R> entityResponse, T entityToSave) {

        T saved = systemDao.persist(entityToSave);

        R response = null;
        try {
            response = entityResponse.newInstance();
            response.set(saved);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return response;
    }

}
