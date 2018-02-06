package com.fral.extreme.s4.services;

import com.fral.extreme.s4.common.dto.request.ClassUpdateRequestDto;
import com.fral.extreme.s4.common.dto.request.StudentRequestDto;
import com.fral.extreme.s4.common.dto.request.StudentUpdateRequestDto;
import com.fral.extreme.s4.common.dto.response.StudentResponseDto;
import com.fral.extreme.s4.common.dto.response.StudentShortInfoDto;
import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.model.Student;
import com.fral.extreme.s4.domain.repository.S4SystemDao;
import com.fral.extreme.s4.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class StudentService {

    @Resource
    private S4SystemDao systemDao;


    public StudentResponseDto find(Long studentId) throws EntityNotFoundException {

        Student retrievedStudent = systemDao.load(Student.class, studentId);

        if (retrievedStudent == null) {
            throw new EntityNotFoundException();
        }

        StudentResponseDto response = new StudentResponseDto(retrievedStudent);

        return response;
    }

    public Set<StudentResponseDto> getAll() {
        Set<StudentResponseDto> resultCollection = new HashSet<>();

        Collection<Student> retrievedStudents = systemDao.find(Student.class);

        for (Student entity : retrievedStudents) {
            StudentResponseDto response = new StudentResponseDto(entity);

            resultCollection.add(response);
        }

        return resultCollection;
    }

    public StudentResponseDto save(StudentRequestDto student) {
        Student newStudent = new Student(student.getLastName(), student.getFirstName());

        Student persistedStudent = null;
        try {
            persistedStudent = systemDao.persist(newStudent);
        } catch (Exception ex) {
            throw new PersistenceException();
        }

        StudentResponseDto responseDto = new StudentResponseDto(persistedStudent);

        return responseDto;
    }

    public StudentResponseDto update(StudentUpdateRequestDto student) throws EntityNotFoundException {
        Student toUpdate = systemDao.load(Student.class, student.getId());
        if (toUpdate == null) {
            throw new EntityNotFoundException();
        }

        toUpdate.setFirstName(student.getFirstName());
        toUpdate.setLastName(student.getLastName());

        Student updated = null;

        try {
            updated = systemDao.persist(toUpdate);
        } catch (Exception ex) {
            throw new PersistenceException();
        }

        return new StudentResponseDto(updated);
    }

    public boolean delete(Long studentId) {
        Student toDelete = systemDao.load(Student.class, studentId);

        return systemDao.delete(toDelete);
    }

    public Set<StudentShortInfoDto> getStudentsRegisteredToClass(Long classId) throws EntityNotFoundException {
        Class registeredClass = systemDao.load(Class.class, classId);

        if (registeredClass == null) {
            throw new EntityNotFoundException();
        }

        Set<StudentShortInfoDto> responseDtoList = new HashSet<>();
        for (Student entity : registeredClass.getStudents()) {
            StudentShortInfoDto dto = new StudentShortInfoDto();
            dto.setId(entity.getId());
            dto.setFirstName(entity.getFirstName());
            dto.setLastName(entity.getLastName());

            responseDtoList.add(dto);
        }

        return responseDtoList;
    }

    public Set<StudentResponseDto> findByProperty(String studentProperty) {
        return null;
    }

    public boolean takeClass(Long studentId, ClassUpdateRequestDto newClass) throws EntityNotFoundException {
        Student retrievedStudent = systemDao.load(Student.class, studentId);

        if (retrievedStudent == null) {
            throw new EntityNotFoundException();
        }

        Class classToBeAdded = new Class(newClass.getCode(), newClass.getTitle(), newClass.getDescription());
        classToBeAdded.setId(newClass.getId());

        retrievedStudent.setClass(classToBeAdded);

        Student savedStudent;
        try {
            savedStudent = systemDao.persist(retrievedStudent);
        } catch (Exception ex) {
            throw new PersistenceException();
        }
        return savedStudent != null;
    }

    public boolean takeClasses(Long studentId, Set<ClassUpdateRequestDto> classes) throws EntityNotFoundException {
        Student retrievedStudent = systemDao.load(Student.class, studentId);

        if (retrievedStudent == null) {
            throw new EntityNotFoundException();
        }

        for (ClassUpdateRequestDto entity : classes) {
            Class newClass = new Class(entity.getCode(), entity.getTitle(), entity.getDescription());
            newClass.setId(entity.getId());

            retrievedStudent.setClass(newClass);
        }

        Student savedStudent;
        try {
            savedStudent = systemDao.persist(retrievedStudent);
        } catch (Exception ex) {
            throw new PersistenceException();
        }

        return savedStudent != null;
    }
}
