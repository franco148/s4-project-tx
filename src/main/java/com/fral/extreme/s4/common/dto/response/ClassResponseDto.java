package com.fral.extreme.s4.common.dto.response;

import com.fral.extreme.s4.common.dto.DtoBase;
import com.fral.extreme.s4.common.dto.request.StudentRequestDto;
import com.fral.extreme.s4.domain.model.BaseEntity;
import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.model.Student;

import java.util.Collection;
import java.util.HashSet;

public class ClassResponseDto extends DtoBase {

    //region Properties
    private String code;
    private String title;
    private String description;
    private Collection<StudentRequestDto> registeredStudents;
    //endregion

    //region Getters & Setters
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

    public Collection<StudentRequestDto> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(Collection<StudentRequestDto> registeredStudents) {
        if (this.registeredStudents == null)
            this.registeredStudents = new HashSet<>();

        this.registeredStudents.addAll(registeredStudents);
    }

    public void addNewStudent(StudentRequestDto student) {
        if (this.registeredStudents == null)
            this.registeredStudents = new HashSet<>();

        this.registeredStudents.add(student);
    }
    //endregion

    //region Overrides
    @Override
    public <T extends BaseEntity> void set(T entity) {

        if (entity instanceof Class) {
            Class classInfo = Class.class.cast(entity);

            this.id = classInfo.getId();
            this.code = classInfo.getCode();
            this.title = classInfo.getTitle();
            this.description = classInfo.getDescription();

            if (classInfo.getStudents() != null) {
                for (Student student : classInfo.getStudents()) {
                    StudentRequestDto studentDto = new StudentRequestDto();
                    studentDto.setId(student.getId());
                    studentDto.setFirstName(student.getFirstName());
                    studentDto.setLastName(student.getLastName());

                    this.addNewStudent(studentDto);
                }
            }
        }
    }
    //endregion

}
