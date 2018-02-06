package com.fral.extreme.s4.common.dto.response;

import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.model.Student;

import java.util.Collection;
import java.util.HashSet;

public class ClassResponseDto {

    private Long id;
    private String code;
    private String title;
    private String description;
    private Collection<StudentShortInfoDto> registeredStudents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Collection<StudentShortInfoDto> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(Collection<StudentShortInfoDto> registeredStudents) {
        this.registeredStudents = new HashSet<>();
        this.registeredStudents.addAll(registeredStudents);
    }

    public void addNewStudent(StudentShortInfoDto student) {
        this.registeredStudents.add(student);
    }

    public static ClassResponseDto buildFrom(Class classInfo) {
        ClassResponseDto response = new ClassResponseDto();

        response.setId(classInfo.getId());
        response.setCode(classInfo.getCode());
        response.setTitle(classInfo.getTitle());
        response.setDescription(classInfo.getDescription());

        if (classInfo.getStudents() != null) {
            for (Student student : classInfo.getStudents()) {
                StudentShortInfoDto studentDto = new StudentShortInfoDto();
                studentDto.setId(student.getId());
                studentDto.setFirstName(student.getFirstName());
                studentDto.setLastName(student.getLastName());

                response.addNewStudent(studentDto);
            }
        }

        return response;
    }
}
