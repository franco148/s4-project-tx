package com.fral.extreme.s4.services;

import com.fral.extreme.s4.common.dto.request.ClassRequestDto;
import com.fral.extreme.s4.common.dto.request.ClassUpdateRequestDto;
import com.fral.extreme.s4.common.dto.request.StudentUpdateRequestDto;
import com.fral.extreme.s4.common.dto.response.ClassResponseDto;
import com.fral.extreme.s4.common.dto.response.ClassShortInfoDto;
import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.model.Student;
import com.fral.extreme.s4.domain.repository.S4SystemDao;
import com.fral.extreme.s4.domain.repository.S4SystemMockDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ClassService {

    @Resource
    private S4SystemDao systemDao;

    public ClassResponseDto find(Long classId) {
        Class retrievedClass = systemDao.load(Class.class, classId);

        ClassResponseDto response = ClassResponseDto.buildFrom(retrievedClass);

        return response;
    }

    public Set<ClassResponseDto> getAll() {
        Set<ClassResponseDto> resultCollection = new HashSet<>();

        Collection<Class> retrievedClasses = systemDao.find(Class.class);

        for (Class entity : retrievedClasses) {
            ClassResponseDto response = ClassResponseDto.buildFrom(entity);

            resultCollection.add(response);
        }

        return resultCollection;
    }

    public ClassResponseDto save(ClassRequestDto classToSave) {
        Class newClass = new Class(classToSave.getCode(), classToSave.getTitle(), classToSave.getDescription());

        Class persistedClass = systemDao.persist(newClass);

        ClassResponseDto responseDto = ClassResponseDto.buildFrom(persistedClass);

        return responseDto;
    }

    public ClassResponseDto update(ClassUpdateRequestDto classToUpdate) {
        Class update = systemDao.load(Class.class, classToUpdate.getId());

        update.setCode(classToUpdate.getCode());
        update.setTitle(classToUpdate.getTitle());
        update.setDescription(classToUpdate.getDescription());

        Class updated = systemDao.persist(update);

        ClassResponseDto requestDto = ClassResponseDto.buildFrom(updated);

        return requestDto;
    }

    public boolean delete(Long classId) {
        Class toBeDeleted = systemDao.load(Class.class, classId);

        return systemDao.delete(toBeDeleted);
    }

    public Set<ClassShortInfoDto> getClassesAssignedToStudent(Long studentId) {
        Student student = systemDao.load(Student.class, studentId);

        Set<ClassShortInfoDto> responseDtoList = new HashSet<>();
        for (Class entity : student.getClasses()) {
            ClassShortInfoDto dto = new ClassShortInfoDto();
            dto.setId(entity.getId());
            dto.setCode(entity.getCode());
            dto.setTitle(entity.getTitle());
            dto.setDescription(entity.getDescription());

            responseDtoList.add(dto);
        }

        return responseDtoList;
    }

    public Set<ClassResponseDto> findByProperty(String classProperty) {
        return null;
    }

    public boolean registerNewStudent(Long classId, StudentUpdateRequestDto student) {
        Class retrievedClass = systemDao.load(Class.class, classId);

        Student newStudent = new Student(student.getLastName(), student.getFirstName());
        newStudent.setId(student.getId());

        retrievedClass.setStudent(newStudent);

        Class savedClass = systemDao.persist(retrievedClass);

        return savedClass != null;
    }

    public boolean registerStudents(Long classId, Set<StudentUpdateRequestDto> students) {
        Class retrievedClass = systemDao.load(Class.class, classId);

        for (StudentUpdateRequestDto entity : students) {
            Student newStudent = new Student(entity.getLastName(), entity.getFirstName());
            newStudent.setId(entity.getId());

            retrievedClass.setStudent(newStudent);
        }

        Class savedClass = systemDao.persist(retrievedClass);

        return savedClass != null;
    }
}
