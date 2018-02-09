package com.fral.extreme.s4.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@Entity
public class Student extends BaseEntity {

    //region Properties
    private String lastName;

    private String firstName;

    @Column
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Class_Student",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "class_id") }
    )
    private List<Class> classes;
    //endregion

    //region Constructor
    public Student() {
    }

    public Student(Long id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }
    //endregion

    //region Getters & Setters
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = new ArrayList<>();
        this.classes.addAll(classes);
    }

    public void setClass(Class newClass) {
        this.classes.add(newClass);
    }
    //endregion

    //region Overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(firstName, student.firstName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, lastName, firstName, classes);
    }
    //endregion
}