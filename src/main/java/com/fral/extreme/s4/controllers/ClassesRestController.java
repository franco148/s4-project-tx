package com.fral.extreme.s4.controllers;

import com.fral.extreme.s4.common.dto.request.ClassRequestDto;
import com.fral.extreme.s4.common.dto.request.ClassUpdateRequestDto;
import com.fral.extreme.s4.common.dto.request.StudentUpdateRequestDto;
import com.fral.extreme.s4.common.dto.response.ClassResponseDto;
import com.fral.extreme.s4.common.dto.response.ClassShortInfoDto;
import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.exception.EntityNotFoundException;
import com.fral.extreme.s4.exception.PersistenceException;
import com.fral.extreme.s4.services.ClassService;
import com.fral.extreme.s4.services.S4SystemService;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;

@RequestMapping(value = "/classes")
@RestController
@Scope("session")
public class ClassesRestController {

    @Resource
    private ClassService studentService;

    @Resource
    private S4SystemService systemService;


    @RequestMapping(
            value = "/{classId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<ClassResponseDto> find(@PathVariable("classId") Long classId) throws EntityNotFoundException {
        //return ResponseEntity.ok(studentService.find(classId));
        //return ResponseEntity.ok(systemService.find(ClassResponseDto.class, Class.class, classId));
        return null;
    }

//    @RequestMapping(
//            method = RequestMethod.GET
//    )
//    public ResponseEntity<Set<ClassResponseDto>> getAll() {
//        return ResponseEntity.ok(studentService.getAll());
//    }
//
//    @RequestMapping(
//            method = RequestMethod.POST
//    )
//    public ResponseEntity<ClassResponseDto> save(@RequestBody ClassRequestDto requestBody) throws PersistenceException {
//        return ResponseEntity.ok(studentService.save(requestBody));
//    }
//
//    @RequestMapping(
//            method = RequestMethod.PATCH
//    )
//    public ResponseEntity<ClassResponseDto> update(@RequestBody ClassUpdateRequestDto classRequestBody) throws PersistenceException, EntityNotFoundException {
//        return ResponseEntity.ok(studentService.update(classRequestBody));
//    }
//
//    @RequestMapping(
//            value = "/{classId}",
//            method = RequestMethod.DELETE
//    )
//    public ResponseEntity<String> delete(@PathVariable("classId") Long classId) {
//        String response = studentService.delete(classId) ? "{'message':'Operation completed SUCCESSFULLY'}" : "{'message':'Operation has FAILED'}";
//
//        return ResponseEntity.ok(response);
//    }
//
//    @RequestMapping(
//            value = "/byStudentId",
//            method = RequestMethod.GET
//    )
//    public ResponseEntity<Set<ClassShortInfoDto>> getClassesByStudent(@RequestParam("studentId") Long studentId) throws EntityNotFoundException {
//        return ResponseEntity.ok(studentService.getClassesAssignedToStudent(studentId));
//    }
//
//    @RequestMapping(
//            value = "/{classId}",
//            method = RequestMethod.POST
//    )
//    public ResponseEntity<String> registerInNewClass(@PathVariable("classId") Long classId,
//                                                     @RequestBody StudentUpdateRequestDto newClass) throws PersistenceException, EntityNotFoundException {
//        String response = studentService.registerNewStudent(classId, newClass) ? "{'message':'Operation completed SUCCESSFULLY'}" : "{'message':'Operation has FAILED'}";
//
//        return ResponseEntity.ok(response);
//    }
//
//    @RequestMapping(
//            value = "/{classId}/classes",
//            method = RequestMethod.POST
//    )
//    public ResponseEntity<String> registerInManyClasses(@PathVariable("classId") Long classId,
//                                                        @RequestBody Set<StudentUpdateRequestDto> newStudents) throws PersistenceException, EntityNotFoundException {
//        String response = studentService.registerStudents(classId, newStudents) ? "{'message':'Operation completed SUCCESSFULLY'}" : "{'message':'Operation has FAILED'}";
//
//        return ResponseEntity.ok(response);
//    }
}
