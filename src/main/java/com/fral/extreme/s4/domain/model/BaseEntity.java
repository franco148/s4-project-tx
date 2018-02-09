package com.fral.extreme.s4.domain.model;

import com.fral.extreme.s4.exception.IncompatibleEntityTypeException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.Class;
import java.util.Collection;

@Entity
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract  <T extends BaseEntity> void copy(T entity) throws IncompatibleEntityTypeException;
    public abstract <T extends BaseEntity> void addRelatedEntity(T relatedEntity) throws IncompatibleEntityTypeException;
    public abstract <T extends BaseEntity> Collection<T> getRelatedEntities(Class<T> type);
}
