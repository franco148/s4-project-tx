package com.fral.extreme.s4.services;

import com.fral.extreme.s4.common.dto.request.ClassRequestDto;
import com.fral.extreme.s4.common.dto.request.StudentRequestDto;
import com.fral.extreme.s4.common.dto.response.StudentResponseDto;
import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.repository.S4SystemDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class StudentService {

    @Resource
    private S4SystemDao systemDao;


    public StudentResponseDto find(Long studentId) {
        return null;
    }

    public Set<StudentResponseDto> getAll() {
        return null;
    }

    public StudentResponseDto save(StudentRequestDto student) {
        return null;
    }

    public StudentResponseDto update(StudentRequestDto student) {
        return null;
    }

    public boolean delete(Long studentId) {
        return true;
    }

    public Set<StudentResponseDto> getStudentsRegisteredToClass(Long classId) {
        return null;
    }

    public Set<StudentResponseDto> findByProperty(String studentProperty) {
        return null;
    }

    public boolean takeClass(Long studentId, Class newClass) {
        return true;
    }

    public boolean takeClasses(Long studentId, Set<ClassRequestDto> classes) {
        return true;
    }
}
