package com.fral.extreme.s4.services;

import com.fral.extreme.s4.common.dto.StudentRequestDto;
import com.fral.extreme.s4.common.dto.StudentResponseDto;
import com.fral.extreme.s4.domain.repository.S4SystemDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

@Service
public class StudentService {

    @Resource
    private S4SystemDao systemDao;


    public StudentResponseDto find(Long studentId) {
        return null;
    }

    public Collection<StudentResponseDto> getAll() {
        return null;
    }

    public StudentResponseDto save(StudentRequestDto classToSave) {
        return null;
    }

    public StudentResponseDto update(StudentRequestDto classToUpdate) {
        return null;
    }

    public boolean delete(Long classId) {
        return true;
    }

    public Collection<StudentResponseDto> getClassesAssignedToStudent(Long studentId) {
        return null;
    }

    public Collection<StudentResponseDto> findByProperty(String Property) {
        return null;
    }
}
