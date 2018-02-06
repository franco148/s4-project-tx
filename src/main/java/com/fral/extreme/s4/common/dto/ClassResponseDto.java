package com.fral.extreme.s4.common.dto;

import com.fral.extreme.s4.domain.model.Student;

import java.util.Collection;
import java.util.HashSet;

public class ClassResponseDto {

    private Long id;
    private String code;
    private String title;
    private String description;
    private Collection<Student> registeredStudents;

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

    public Collection<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(Collection<Student> registeredStudents) {
        this.registeredStudents = new HashSet<>();
        this.registeredStudents.addAll(registeredStudents);
    }
}
