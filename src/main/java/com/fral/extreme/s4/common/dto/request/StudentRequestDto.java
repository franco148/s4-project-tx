package com.fral.extreme.s4.common.dto.request;

import com.fral.extreme.s4.common.dto.DtoBase;
import com.fral.extreme.s4.domain.model.BaseEntity;
import com.fral.extreme.s4.domain.model.Student;
import com.fral.extreme.s4.exception.IncompatibleEntityTypeException;

public class StudentRequestDto extends DtoBase {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public <T extends BaseEntity> void set(T entity) throws IncompatibleEntityTypeException {
        if (entity instanceof Student) {
            Student sourceEntity = Student.class.cast(entity);

            this.id = sourceEntity.getId();
            this.firstName = sourceEntity.getFirstName();
            this.lastName = sourceEntity.getLastName();
        } else {
            throw new IncompatibleEntityTypeException("Set entity to DTO", entity.getClass().getTypeName(), Student.class.getTypeName());
        }
    }
}
