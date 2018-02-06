package com.fral.extreme.s4.services;

import com.fral.extreme.s4.common.dto.ClassRequestDto;
import com.fral.extreme.s4.common.dto.ClassResponseDto;
import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.model.Student;
import com.fral.extreme.s4.domain.repository.S4SystemDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class ClassService {

    @Resource
    private S4SystemDao systemDao;

    public ClassResponseDto find(Long classId) {
        return null;
    }

    public Set<ClassResponseDto> getAll() {
        return null;
    }

    public ClassResponseDto save(ClassRequestDto classToSave) {
        return null;
    }

    public ClassResponseDto update(ClassRequestDto classToUpdate) {
        return null;
    }

    public boolean delete(Long classId) {
        return true;
    }

    public Set<ClassResponseDto> getClassesAssignedToStudent(Long studentId) {
        return null;
    }

    public Set<ClassResponseDto> findByProperty(String classProperty) {
        return null;
    }

    public boolean registerNewStudent(Long classId, Student student) {
        return true;
    }

    public boolean registerStudents(Long classId, Set<Student> students) {
        return true;
    }
}
