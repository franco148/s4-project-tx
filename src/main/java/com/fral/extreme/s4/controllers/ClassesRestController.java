package com.fral.extreme.s4.controllers;

import com.fral.extreme.s4.common.dto.DtoBase;
import com.fral.extreme.s4.common.dto.request.ClassRequestDto;
import com.fral.extreme.s4.common.dto.request.StudentRequestDto;
import com.fral.extreme.s4.common.dto.response.ClassResponseDto;
import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.model.Student;
import com.fral.extreme.s4.exception.InvalidRequestBodyException;
import com.fral.extreme.s4.services.S4SystemService;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@RequestMapping(value = "/classes")
@RestController
@Scope("session")
public class ClassesRestController {

    @Resource
    private S4SystemService<ClassResponseDto, Class, Student> systemService;


    @RequestMapping(
            value = "/{classId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<DtoBase> find(@PathVariable("classId") Long classId) {

        ClassResponseDto response = systemService.find(ClassResponseDto.class, Class.class, classId);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public ResponseEntity<Set<ClassResponseDto>> getAll() {

        Set<ClassResponseDto> response = systemService.getAll(ClassResponseDto.class, Class.class);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            method = RequestMethod.POST
    )
    public ResponseEntity<ClassResponseDto> save(@RequestBody ClassRequestDto requestBody) {

        if (null == requestBody) {
            throw new InvalidRequestBodyException(requestBody);
        }

        Class classToSave = new Class(requestBody.getId(), requestBody.getCode(), requestBody.getTitle(), requestBody.getDescription());

        ClassResponseDto response = systemService.save(ClassResponseDto.class, classToSave);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            method = RequestMethod.PATCH
    )
    public ResponseEntity<ClassResponseDto> update(@RequestBody ClassRequestDto requestBody) {

        if (null == requestBody) {
            throw new InvalidRequestBodyException(requestBody);
        }

        Class classToUpdate = new Class(requestBody.getId(), requestBody.getCode(), requestBody.getTitle(), requestBody.getDescription());

        ClassResponseDto response = systemService.update(ClassResponseDto.class, Class.class, classToUpdate);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            value = "/{classId}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<String> delete(@PathVariable("classId") Long classId) {

        boolean isDeleted = systemService.delete(Class.class, classId);

        String response = isDeleted ? "{'message':'Operation completed SUCCESSFULLY'}" : "{'message':'Operation has FAILED'}";

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            value = "/byStudentId",
            method = RequestMethod.GET
    )
    public ResponseEntity<Set<ClassResponseDto>> getClassesByStudent(@RequestParam("studentId") Long studentId) {

        Set<ClassResponseDto> response = systemService.getCollectionOfRelatedEntity(ClassResponseDto.class, Class.class, studentId);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            value = "/{classId}",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> registerInNewClass(@PathVariable("classId") Long classId,
                                                     @RequestBody StudentRequestDto newStudent) {

        if (null == newStudent) {
            throw new InvalidRequestBodyException(newStudent);
        }

        Student studentToRegister = new Student(newStudent.getId(), newStudent.getLastName(), newStudent.getFirstName());
        boolean isAddedSuccessfully = systemService.addRelatedEntity(Class.class, classId, studentToRegister);

        String response = isAddedSuccessfully ? "{'message':'Operation completed SUCCESSFULLY'}" : "{'message':'Operation has FAILED'}";

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            value = "/{classId}/classes",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> registerInManyClasses(@PathVariable("classId") Long classId,
                                                        @RequestBody Set<StudentRequestDto> newStudents) {

        if (null == newStudents) {
            throw new InvalidRequestBodyException(newStudents);
        }

        Set<Student> studentsToRegister = new HashSet<>();
        for (StudentRequestDto dto : newStudents) {
            Student student = new Student(dto.getId(), dto.getLastName(), dto.getFirstName());
            studentsToRegister.add(student);
        }

        boolean areRegisteredSuccessfully = systemService.addRelatedEntities(Class.class, classId, studentsToRegister);
        String response = areRegisteredSuccessfully ? "{'message':'Operation completed SUCCESSFULLY'}" : "{'message':'Operation has FAILED'}";

        return ResponseEntity.ok(response);
    }
}
