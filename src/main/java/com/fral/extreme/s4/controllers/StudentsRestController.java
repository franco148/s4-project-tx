package com.fral.extreme.s4.controllers;

import com.fral.extreme.s4.common.dto.request.ClassUpdateRequestDto;
import com.fral.extreme.s4.common.dto.request.StudentRequestDto;
import com.fral.extreme.s4.common.dto.request.StudentUpdateRequestDto;
import com.fral.extreme.s4.common.dto.response.StudentResponseDto;;
import com.fral.extreme.s4.common.dto.response.StudentShortInfoDto;
import com.fral.extreme.s4.domain.model.Student;
import com.fral.extreme.s4.exception.EntityNotFoundException;
import com.fral.extreme.s4.services.S4SystemService;
import com.fral.extreme.s4.services.StudentService;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;

@RequestMapping(value = "/students")
@RestController
@Scope("session")
public class StudentsRestController {

    @Resource
    private StudentService studentService;

    @Resource
    private S4SystemService systemService;


    @RequestMapping(
            value = "/{studentId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<StudentResponseDto> find(@PathVariable("studentId") Long studentId) throws EntityNotFoundException {
        //return ResponseEntity.ok(studentService.find(studentId));
        return ResponseEntity.ok(systemService.find(StudentResponseDto.class, Student.class, studentId));
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public ResponseEntity<Set<StudentResponseDto>> getAll() {
        //return ResponseEntity.ok(studentService.getAll());
        return ResponseEntity.ok(systemService.getAll(StudentResponseDto.class, Student.class));
    }

    @RequestMapping(
            method = RequestMethod.POST
    )
    public ResponseEntity<StudentResponseDto> save(@RequestBody StudentRequestDto requestBody) {
        Student student = new Student(0L, requestBody.getLastName(), requestBody.getFirstName());
        //return ResponseEntity.ok(studentService.save(requestBody));
        return ResponseEntity.ok(systemService.save(StudentResponseDto.class, student));
    }

//    @RequestMapping(
//            method = RequestMethod.PATCH
//    )
//    public ResponseEntity<StudentResponseDto> update(@RequestBody StudentUpdateRequestDto student) throws EntityNotFoundException {
//        return ResponseEntity.ok(studentService.update(student));
//    }
//
//    @RequestMapping(
//            value = "/{studentId}",
//            method = RequestMethod.DELETE
//    )
//    public ResponseEntity<String> delete(@PathVariable("studentId") Long studentId) {
//        String response = studentService.delete(studentId) ? "{'message':'Operation completed SUCCESSFULLY'}" : "{'message':'Operation has FAILED'}";
//
//        return ResponseEntity.ok(response);
//    }
//
//    @RequestMapping(
//            value = "/byClassId",
//            method = RequestMethod.GET
//    )
//    public ResponseEntity<Set<StudentShortInfoDto>> getStudentsByClass(@RequestParam("classId") Long classId) throws EntityNotFoundException {
//        return ResponseEntity.ok(studentService.getStudentsRegisteredToClass(classId));
//    }
//
//    @RequestMapping(
//            value = "/{studentId}",
//            method = RequestMethod.POST
//    )
//    public ResponseEntity<String> registerInNewClass(@PathVariable("studentId") Long studentId,
//                                                     @RequestBody ClassUpdateRequestDto newClass) throws EntityNotFoundException {
//        String response = studentService.takeClass(studentId, newClass) ? "{'message':'Operation completed SUCCESSFULLY'}" : "{'message':'Operation has FAILED'}";
//
//        return ResponseEntity.ok(response);
//    }
//
//    @RequestMapping(
//            value = "/{studentId}/classes",
//            method = RequestMethod.POST
//    )
//    public ResponseEntity<String> registerInManyClasses(@PathVariable("studentId") Long studentId,
//                                                        @RequestBody Set<ClassUpdateRequestDto> newClasses) throws EntityNotFoundException {
//        String response = studentService.takeClasses(studentId, newClasses) ? "{'message':'Operation completed SUCCESSFULLY'}" : "{'message':'Operation has FAILED'}";
//
//        return ResponseEntity.ok(response);
//    }
}
