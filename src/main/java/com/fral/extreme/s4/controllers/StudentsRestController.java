package com.fral.extreme.s4.controllers;

import com.fral.extreme.s4.common.dto.request.ClassRequestDto;
import com.fral.extreme.s4.common.dto.request.StudentRequestDto;
import com.fral.extreme.s4.common.dto.response.StudentResponseDto;
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


@RequestMapping(value = "/students")
@RestController
@Scope("session")
public class StudentsRestController {

    @Resource
    private S4SystemService<StudentResponseDto, Student, Class> systemService;


    @RequestMapping(
            value = "/{studentId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<StudentResponseDto> find(@PathVariable("studentId") Long studentId) {

        StudentResponseDto response = systemService.find(StudentResponseDto.class, Student.class, studentId);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public ResponseEntity<Set<StudentResponseDto>> getAll() {

        Set<StudentResponseDto> response = systemService.getAll(StudentResponseDto.class, Student.class);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            method = RequestMethod.POST
    )
    public ResponseEntity<StudentResponseDto> save(@RequestBody StudentRequestDto requestBody) {

        if (null == requestBody) {
            throw new InvalidRequestBodyException(requestBody);
        }

        Student studentToSave = new Student(requestBody.getId(), requestBody.getLastName(), requestBody.getFirstName());

        StudentResponseDto response = systemService.save(StudentResponseDto.class, studentToSave);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            method = RequestMethod.PATCH
    )
    public ResponseEntity<StudentResponseDto> update(@RequestBody StudentRequestDto studentDto) {

        if (null == studentDto) {
            throw new InvalidRequestBodyException(studentDto);
        }

        Student studentToUpdate = new Student(studentDto.getId(), studentDto.getLastName(), studentDto.getFirstName());

        StudentResponseDto response = systemService.update(StudentResponseDto.class, Student.class, studentToUpdate);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            value = "/{studentId}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<String> delete(@PathVariable("studentId") Long studentId) {

        boolean isDeleted = systemService.delete(Student.class, studentId);

        String response = isDeleted ? "{'message':'Operation completed SUCCESSFULLY'}" : "{'message':'Operation has FAILED'}";

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            value = "/byClassId",
            method = RequestMethod.GET
    )
    public ResponseEntity<Set<StudentResponseDto>> getStudentsByClass(@RequestParam("classId") Long classId) {

        Set<StudentResponseDto> response = systemService.getCollectionOfRelatedEntity(StudentResponseDto.class, Student.class, classId);

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            value = "/{studentId}",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> registerInNewClass(@PathVariable("studentId") Long studentId,
                                                     @RequestBody ClassRequestDto newClass) {
        if (null == newClass) {
            throw new InvalidRequestBodyException(newClass);
        }

        Class classToTake = new Class(newClass.getId(), newClass.getCode(), newClass.getTitle(), newClass.getDescription());
        boolean isTookSuccessfully = systemService.addRelatedEntity(Student.class, studentId, classToTake);

        String response = isTookSuccessfully ? "{'message':'Operation completed SUCCESSFULLY'}" : "{'message':'Operation has FAILED'}";

        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            value = "/{studentId}/classes",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> registerInManyClasses(@PathVariable("studentId") Long studentId,
                                                        @RequestBody Set<ClassRequestDto> newClasses) {
        if (null == newClasses) {
            throw new InvalidRequestBodyException(newClasses);
        }

        Set<Class> classesToTake = new HashSet<>();
        for (ClassRequestDto dto : newClasses) {
            Class classToTake = new Class(dto.getId(), dto.getCode(), dto.getTitle(), dto.getDescription());
            classesToTake.add(classToTake);
        }

        boolean areTakenSuccessfully = systemService.addRelatedEntities(Student.class, studentId, classesToTake);
        String response = areTakenSuccessfully ? "{'message':'Operation completed SUCCESSFULLY'}" : "{'message':'Operation has FAILED'}";

        return ResponseEntity.ok(response);
    }
}
