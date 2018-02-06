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
        this.classes = new HashSet<>();
        this.classes.addAll(classes);
    }

    public void addNewClass(ClassShortInfoDto newClass) {
        this.classes.add(newClass);
    }

    public static StudentResponseDto buildFrom(Student studentInfo) {
        StudentResponseDto response = new StudentResponseDto();

        response.setId(studentInfo.getId());
        response.setFirstName(studentInfo.getFirstName());
        response.setLastName(studentInfo.getLastName());

        if (studentInfo.getClasses() != null) {
            for (Class entity : studentInfo.getClasses()) {
                ClassShortInfoDto classDto = new ClassShortInfoDto();
                classDto.setId(entity.getId());
                classDto.setCode(entity.getCode());
                classDto.setTitle(entity.getTitle());
                classDto.setDescription(entity.getDescription());

                response.addNewClass(classDto);
            }
        }

        return response;
    }
}
