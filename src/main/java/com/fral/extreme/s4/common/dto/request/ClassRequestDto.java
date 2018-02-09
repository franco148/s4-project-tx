package com.fral.extreme.s4.common.dto.request;

import com.fral.extreme.s4.common.dto.DtoBase;
import com.fral.extreme.s4.domain.model.BaseEntity;
import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.exception.IncompatibleEntityTypeException;

public class ClassRequestDto extends DtoBase {

    private String code;
    private String title;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public <T extends BaseEntity> void set(T entity) throws IncompatibleEntityTypeException {
        if (entity instanceof Class) {
            Class sourceEntity = Class.class.cast(entity);

            this.id = sourceEntity.getId();
            this.code = sourceEntity.getCode();
            this.title = sourceEntity.getTitle();
            this.description = sourceEntity.getDescription();

        } else {
            throw new IncompatibleEntityTypeException();
        }
    }
}
