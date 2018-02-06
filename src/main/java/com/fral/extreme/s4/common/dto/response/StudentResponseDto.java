package com.fral.extreme.s4.common.dto.response;

import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.model.Student;

import java.util.Collection;
import java.util.HashSet;

public class StudentResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Collection<ClassShortInfoDto> classes;

    public StudentResponseDto(Student studentModel) {
        this.initializeFromModel(studentModel);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Collection<ClassShortInfoDto> getClasses() {
        return classes;
    }

    public void setClasses(Collection<ClassShortInfoDto> classes) {
        if (this.classes == null)
            this.classes = new HashSet<>();

        this.classes.addAll(classes);
    }

    public void addNewClass(ClassShortInfoDto newClass) {
        if (this.classes == null)
            this.classes = new HashSet<>();

        this.classes.add(newClass);
    }

    private void initializeFromModel(Student model) {
        if (model != null) {
            this.id = model.getId();
            this.firstName = model.getFirstName();
            this.lastName = model.getLastName();

            if (model.getClasses() != null) {
                for (Class entity : model.getClasses()) {
                    ClassShortInfoDto classDto = new ClassShortInfoDto();
                    classDto.setId(entity.getId());
                    classDto.setCode(entity.getCode());
                    classDto.setTitle(entity.getTitle());
                    classDto.setDescription(entity.getDescription());

                    this.addNewClass(classDto);
                }
            }
        }
    }
}
