package com.fral.extreme.s4.services;

import com.fral.extreme.s4.common.dto.ClassRequestDto;
import com.fral.extreme.s4.common.dto.ClassResponseDto;
import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.repository.S4SystemDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

@Service
public class ClassService {

    @Resource
    private S4SystemDao systemDao;

    public ClassResponseDto find(Long classId) {
        return null;
    }

    public Collection<ClassResponseDto> getAll() {
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

    public Collection<ClassResponseDto> getClassesAssignedToStudent(Long studentId) {
        return null;
    }

    public Collection<ClassResponseDto> findByProperty(String Property) {
        return null;
    }
}
