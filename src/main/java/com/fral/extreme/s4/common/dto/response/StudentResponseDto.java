package com.fral.extreme.s4.common.dto.response;

import com.fral.extreme.s4.common.dto.DtoBase;
import com.fral.extreme.s4.common.dto.request.ClassRequestDto;
import com.fral.extreme.s4.domain.model.BaseEntity;
import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.model.Student;
import com.fral.extreme.s4.exception.IncompatibleEntityTypeException;

import java.util.Collection;
import java.util.HashSet;

public class StudentResponseDto extends DtoBase {

    //region Properties
    private String firstName;
    private String lastName;
    private Collection<ClassRequestDto> classes;
    //endregion

    //region Getters & Setters
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

    public Collection<ClassRequestDto> getClasses() {
        return classes;
    }

    public void setClasses(Collection<ClassRequestDto> classes) {
        if (this.classes == null)
            this.classes = new HashSet<>();

        this.classes.addAll(classes);
    }

    public void addNewClass(ClassRequestDto newClass) {
        if (this.classes == null)
            this.classes = new HashSet<>();

        this.classes.add(newClass);
    }
    //endregion

    //region Overrides
    @Override
    public <T extends BaseEntity> void set(T entity) throws IncompatibleEntityTypeException {

        if (entity instanceof Student) {
            Student studentInfo = Student.class.cast(entity);

            this.id = studentInfo.getId();
            this.firstName = studentInfo.getFirstName();
            this.lastName = studentInfo.getLastName();

            if (studentInfo.getClasses() != null) {
                for (Class classInfo : studentInfo.getClasses()) {
                    ClassRequestDto classDto = new ClassRequestDto();
                    classDto.setId(classInfo.getId());
                    classDto.setCode(classInfo.getCode());
                    classDto.setTitle(classInfo.getTitle());
                    classDto.setDescription(classInfo.getDescription());

                    this.addNewClass(classDto);
                }
            }
        } else {
            throw new IncompatibleEntityTypeException("Set entity to DTO", entity.getClass().getTypeName(), Student.class.getTypeName());
        }
    }
    //endregion

}
