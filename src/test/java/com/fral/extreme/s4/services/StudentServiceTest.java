package com.fral.extreme.s4.services;

import com.fral.extreme.s4.common.dto.request.StudentRequestDto;
import com.fral.extreme.s4.common.dto.response.StudentResponseDto;
import com.fral.extreme.s4.config.SpringTestContext;
import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.model.Student;
import com.fral.extreme.s4.domain.repository.S4SystemDao;
import com.fral.extreme.s4.exception.EntityNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = SpringTestContext.class)
public class StudentServiceTest {

    @Mock
    private S4SystemDao systemDao;

    @Autowired
    @InjectMocks
    private StudentService studentService;

    @Before
    public void setUp() throws Exception {
        Student toBeReturnedFromDao = new Student(1L,"TestUserName", "TestUserLastName");
        when(systemDao.load(Student.class, 1L)).thenReturn(toBeReturnedFromDao);
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void testFind_ReturnedValueShouldBe_DtoClass() throws EntityNotFoundException {
//
//        StudentResponseDto actualResult = studentService.find(1L);
//        Long expectedId = 1L;
//
//        assertNotNull(actualResult);
//        assertEquals(expectedId, actualResult.getId());
//    }
//
//    @Test
//    public void testFind_DaoLoadMethod_WithCorrectParameters() throws EntityNotFoundException {
//
//        //Verifying that the persistence layer calls LOAD method
//        studentService.find(1L);
//        verify(systemDao).load(Student.class, 1L);
//    }
//
//    @Test
//    public void testFind_DaoLoadMethod_ExecutedOnce() throws EntityNotFoundException {
//
//        studentService.find(1L);
//        verify(systemDao, Mockito.times(1)).load(Student.class, 1L);
//    }
//
//    @Test
//    public void testFind_DaoLoadMethod_WithWrongParameters() throws EntityNotFoundException {
//
//        studentService.find(1L);
//        verify(systemDao, Mockito.never()).load(Student.class, 10L);
//        verify(systemDao, Mockito.times(0)).load(Student.class, 10L);
//    }
//
//    @Test
//    public void testFind_DaoLoadMethod_NonExecutedMoreThanOnce() throws EntityNotFoundException {
//
//        studentService.find(1L);
//        verify(systemDao, Mockito.atMost(1)).load(Student.class, 1L);
//    }
//
//    @Test
//    public void testGetAll_DaoFindMethod_WithCorrectParameter() {
//        studentService.getAll();
//        verify(systemDao).find(Student.class);
//    }
//
//    @Test
//    public void testGetAll_DaoFindMethod_WithWrongParameter() {
//        studentService.getAll();
//        verify(systemDao, Mockito.never()).find(Class.class);
//    }
//
//    @Test
//    public void testGetAll_DaoFindMethod_ExecutedOnce() {
//        studentService.getAll();
//        verify(systemDao, Mockito.times(1)).find(Student.class);
//    }
//
//    @Test
//    public void testSave_DaoPersistMethod_WithCorrectParameters() {
//        //TODO: Complete unit tests
//    }
//
//    @Test
//    public void testSave_DaoPersistMethod_ExecutedOnce() {
//        //TODO: Complete unit tests
//    }

}