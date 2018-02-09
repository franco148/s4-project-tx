package com.fral.extreme.s4.common.dto;

import com.fral.extreme.s4.domain.model.BaseEntity;
import com.fral.extreme.s4.exception.IncompatibleEntityTypeException;

public abstract class DtoBase {

    //region Properties
    protected Long id;
    //endregion

    //region Constructors
    public DtoBase() {

    }

    public DtoBase(Long id) {
        this.id = id;
    }
    //endregion

    //region Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //endregion

    //region Methods
    public abstract  <T extends BaseEntity> void set(T entity) throws IncompatibleEntityTypeException;
    //endregion
}
