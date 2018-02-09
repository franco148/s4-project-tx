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
public class S4SystemService<TReturn extends DtoBase, TParameter extends BaseEntity> {

    private S4SystemDao systemDao;

    @Autowired
    public S4SystemService(S4SystemDao systemDao) {
        this.systemDao = systemDao;
    }

    public TReturn find(Class<TReturn> responseType, Class<TParameter> parameterType, Serializable entityId) {
        BaseEntity retrievedEntity = systemDao.load(parameterType, entityId);

        if (retrievedEntity == null) {
            throw new EntityNotFoundException(entityId, parameterType.getName());
        }

        TReturn response;
        try {
            response = responseType.newInstance();
            response.set(retrievedEntity);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IncompatibleEntityTypeException("Initializing return type",
                                                      responseType.getTypeName(),
                                                      responseType.getTypeName());
        }

        return response;
    }

    public Set<TReturn> getAll(Class<TReturn> responseType, Class<TParameter> parameterType) {
        Set<TReturn> resultCollection = new HashSet<>();

        Collection<TParameter> retrievedEntities = systemDao.find(parameterType);

        try {
            for (TParameter entity : retrievedEntities) {
                TReturn dto = responseType.newInstance();
                dto.set(entity);

                resultCollection.add(dto);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IncompatibleEntityTypeException("Initializing return type",
                    responseType.getTypeName(),
                    responseType.getTypeName());
        }

        return resultCollection;
    }

    public TReturn save(Class<TReturn> responseType, TParameter entityToSave) {

        TParameter saved = systemDao.persist(entityToSave);

        TReturn response;

        try {

            response = responseType.newInstance();
            response.set(saved);

        } catch (InstantiationException | IllegalAccessException e) {
            throw new IncompatibleEntityTypeException("Initializing return type",
                    responseType.getTypeName(),
                    responseType.getTypeName());
        }

        return response;
    }

    public TReturn update(Class<TReturn> responseType, Class<TParameter> parameterType, TParameter entityToUpdate) {
        TParameter retrievedEntity = systemDao.load(parameterType, entityToUpdate.getId());

        if (retrievedEntity == null) {
            throw new EntityNotFoundException(entityToUpdate.getId(), entityToUpdate.getClass().getName());
        }

        retrievedEntity.copy(entityToUpdate);

        try {
            TReturn response = responseType.newInstance();
            TParameter updatedEntity = systemDao.persist(retrievedEntity);

            response.set(updatedEntity);

            return response;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IncompatibleEntityTypeException("Initializing return type",
                    responseType.getTypeName(),
                    responseType.getTypeName());
        }
    }

    public boolean delete(Class<TParameter> parameterType, Serializable entityId) {

        TParameter entityToDelete = systemDao.load(parameterType, entityId);

        if (entityToDelete == null) {
            throw new EntityNotFoundException(entityId, parameterType.getName());
        }

        return systemDao.delete(entityToDelete);
    }

    public Set<TReturn> getCollectionOfRelatedEntity(Class<TReturn> responseType, Class<TParameter> parameterType, Serializable entityId) {
        TParameter relatedEntity = systemDao.load(parameterType, entityId);
        
        if (relatedEntity != null) {
            throw new EntityNotFoundException(entityId, parameterType.getName());
        }
        
        Set<TReturn> relatedEntitiesResponseList = new HashSet<>();
        Collection<TParameter> relatedEntitiesResult = relatedEntity.getRelatedEntities(parameterType);
        try {
            if (relatedEntitiesResult != null) {
                for (TParameter entity : relatedEntitiesResult) {
                    TReturn dto = responseType.newInstance();
                    dto.set(entity);

                    relatedEntitiesResponseList.add(dto);
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IncompatibleEntityTypeException("Initializing return type",
                    responseType.getTypeName(),
                    responseType.getTypeName());
        }
        
        return relatedEntitiesResponseList;
    }

    public boolean addRelatedEntity(Class<TParameter> parameterType, Serializable entityId, TParameter relatedEntity) {
        TParameter retrievedEntity = systemDao.load(parameterType, entityId);

        if (retrievedEntity == null) {
            throw new EntityNotFoundException(entityId, relatedEntity.getClass().getName());
        }

        retrievedEntity.addRelatedEntity(relatedEntity);

        try {
            TParameter savedEntity = systemDao.persist(retrievedEntity);
            return savedEntity != null;
        } catch (Exception ex) {
            throw new PersistenceException(parameterType.getTypeName());
        }
    }

    public boolean addRelatedEntities(Class<TParameter> parameterType, Serializable entityId, Set<TParameter> relatedEntities) {
        TParameter retrievedEntity = systemDao.load(parameterType, entityId);

        if (retrievedEntity == null) {
            throw new EntityNotFoundException(entityId, parameterType.getName());
        }

        for (TParameter entity : relatedEntities) {
            retrievedEntity.addRelatedEntity(entity);
        }

        try {
            TParameter savedEntity = systemDao.persist(retrievedEntity);
            return savedEntity != null;
        } catch (Exception ex) {
            throw new PersistenceException(parameterType.getTypeName());
        }
    }

}
