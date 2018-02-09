package com.fral.extreme.s4.services;

import com.fral.extreme.s4.common.dto.DtoBase;
import com.fral.extreme.s4.domain.model.BaseEntity;
import com.fral.extreme.s4.domain.repository.S4SystemDao;
import com.fral.extreme.s4.exception.EntityNotFoundException;
import com.fral.extreme.s4.exception.IncompatibleEntityTypeException;
import com.fral.extreme.s4.exception.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class S4SystemService<TReturn, TParameter> {

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
            try {
                response.set(retrievedEntity);
            } catch (IncompatibleEntityTypeException e) {
                e.printStackTrace();
            }
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
                try {
                    dto.set(entity);
                } catch (IncompatibleEntityTypeException e) {
                    e.printStackTrace();
                }

                resultCollection.add(dto);
            } catch (InstantiationException | IllegalAccessException e) {
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
            try {
                response.set(saved);
            } catch (IncompatibleEntityTypeException e) {
                e.printStackTrace();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return response;
    }

    public <R extends DtoBase, T extends BaseEntity> R update(Class<R> entityResponse, Class<T> entityParameter, T entityToUpdate) throws EntityNotFoundException, IncompatibleEntityTypeException {
        T retrievedEntity = systemDao.load(entityParameter, entityToUpdate.getId());

        if (retrievedEntity == null) {
            throw new EntityNotFoundException();
        }

        retrievedEntity.copy(entityToUpdate);

        try {
            R response = entityResponse.newInstance();
            T updatedEntity = systemDao.persist(retrievedEntity);

            response.set(updatedEntity);

            return response;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T extends BaseEntity> boolean delete(Class<T> entityType, Serializable entityId) throws EntityNotFoundException {

        T entityToDelete = systemDao.load(entityType, entityId);

        if (entityToDelete == null) {
            throw new EntityNotFoundException();
        }

        return systemDao.delete(entityToDelete);
    }

    public <R extends DtoBase, T extends BaseEntity> Set<R> getCollectionOfRelatedEntity(Class<R> entityResponse, Class<T> entityParameter, Serializable entityId) throws EntityNotFoundException {
        T relatedEntity = systemDao.load(entityParameter, entityId);
        
        if (relatedEntity != null) {
            throw new EntityNotFoundException();
        }
        
        Set<R> relatedEntitiesResponseList = new HashSet<>();
        Collection<T> relatedEntitiesResult = relatedEntity.getRelatedEntities(entityParameter);
        try {
            if (relatedEntitiesResult != null) {
                for (T entity : relatedEntitiesResult) {
                    R dto = entityResponse.newInstance();
                    try {
                        dto.set(entity);
                    } catch (IncompatibleEntityTypeException e) {
                        e.printStackTrace();
                    }

                    relatedEntitiesResponseList.add(dto);
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        
        return relatedEntitiesResponseList;
    }

    public <T extends BaseEntity, RE extends BaseEntity> boolean addRelatedEntity(Class<T> entityType, Class<RE> relatedEntityType, Serializable entityId, RE relatedEntity) throws EntityNotFoundException, PersistenceException, IncompatibleEntityTypeException {
        T retrievedEntity = systemDao.load(entityType, entityId);

        if (retrievedEntity == null) {
            throw new EntityNotFoundException();
        }

        retrievedEntity.addRelatedEntity(relatedEntity);

        try {
            T savedEntity = systemDao.persist(retrievedEntity);
            return savedEntity != null;
        } catch (Exception ex) {
            throw new PersistenceException();
        }
    }

    public <T extends BaseEntity, RE extends BaseEntity> boolean addRelatedEntities(Class<T> entityType, Class<RE> relatedEntityType, Serializable entityId, Set<RE> relatedEntities) throws EntityNotFoundException, PersistenceException, IncompatibleEntityTypeException {
        T retrievedEntity = systemDao.load(entityType, entityId);

        if (retrievedEntity == null) {
            throw new EntityNotFoundException();
        }

        for (RE entity : relatedEntities) {
            retrievedEntity.addRelatedEntity(entity);
        }

        try {
            T savedEntity = systemDao.persist(retrievedEntity);
            return savedEntity != null;
        } catch (Exception ex) {
            throw new PersistenceException();
        }
    }

}
