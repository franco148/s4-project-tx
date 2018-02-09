package com.fral.extreme.s4.domain.model;

import com.fral.extreme.s4.exception.IncompatibleEntityTypeException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

//@Entity
public class Class extends BaseEntity {

    //region Properties
    private String code;
    private String title;
    private String description;

    @Column
    @ManyToMany(mappedBy = "classes")
    private List<Student> students;
    //endregion

    //region Constructors
    public Class() {
    }

    public Class(Long id, String code, String title, String description) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.description = description;
    }
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = new ArrayList<>();
        this.students.addAll(students);
    }

    public void setStudent(Student student) {
        this.students.add(student);
    }
    //endregion

    //region Overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return Objects.equals(id, aClass.id) &&
                Objects.equals(code, aClass.code) &&
                Objects.equals(title, aClass.title) &&
                Objects.equals(description, aClass.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code, title, description, students);
    }

    @Override
    public <T extends BaseEntity> void copy(T entity) throws IncompatibleEntityTypeException {
        if (entity instanceof Class) {
            Class sourceEntity = Class.class.cast(entity);

            this.id = sourceEntity.getId();
            this.code = sourceEntity.getCode();
            this.title = sourceEntity.getTitle();
            this.description = sourceEntity.getDescription();

            if (sourceEntity.getStudents() != null) {
                for (Student student : sourceEntity.getStudents()) {
                    Student copied = new Student();
                    copied.copy(student);

                    this.setStudent(copied);
                }
            }

        } else {
            throw new IncompatibleEntityTypeException("Copy entity to Class model",
                                                       entity.getClass().getTypeName(),
                                                       Class.class.getTypeName());
        }
    }

    @Override
    public <T extends BaseEntity> void addRelatedEntity(T relatedEntity) throws IncompatibleEntityTypeException {
        if (relatedEntity instanceof Student) {
            setStudent(Student.class.cast(relatedEntity));
        } else {
            throw new IncompatibleEntityTypeException("Add related entity to Class model",
                                                       relatedEntity.getClass().getTypeName(),
                                                       Student.class.getTypeName());
        }
    }

    @Override
    public <T extends BaseEntity> Collection<T> getRelatedEntities(java.lang.Class<T> type) {
        if (type.equals(Student.class)) {
            return (Collection<T>) this.getStudents();
        }

        return null;
    }

    //endregion
}
