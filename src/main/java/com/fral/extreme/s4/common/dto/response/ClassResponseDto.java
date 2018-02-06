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

    public ClassResponseDto(Class classModel) {
        this.initializeFromModel(classModel);
    }

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
        if (this.registeredStudents == null)
            this.registeredStudents = new HashSet<>();

        this.registeredStudents.addAll(registeredStudents);
    }

    public void addNewStudent(StudentShortInfoDto student) {
        if (this.registeredStudents == null)
            this.registeredStudents = new HashSet<>();

        this.registeredStudents.add(student);
    }

    public void initializeFromModel(Class classModel) {
        if (classModel != null) {
            this.id = classModel.getId();
            this.code = classModel.getCode();
            this.title = classModel.getTitle();
            this.description = classModel.getDescription();

            if (classModel.getStudents() != null) {
                for (Student student : classModel.getStudents()) {
                    StudentShortInfoDto studentDto = new StudentShortInfoDto();
                    studentDto.setId(student.getId());
                    studentDto.setFirstName(student.getFirstName());
                    studentDto.setLastName(student.getLastName());

                    this.addNewStudent(studentDto);
                }
            }
        }
    }
}
