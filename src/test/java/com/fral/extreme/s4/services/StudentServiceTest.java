package com.fral.extreme.s4.services;

import com.fral.extreme.s4.common.dto.response.StudentResponseDto;
import com.fral.extreme.s4.config.SpringTestContext;
import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.model.Student;
import com.fral.extreme.s4.domain.repository.S4SystemDao;
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

    //region PrivateProperties
    private final Long STUDENT_ID = 1L;
    private final String STUDENT_NAME = "TestUserName";
    private final String STUDENT_LAST_NAME = "TestUserLastName";
    private final Long NON_EXISTING_STUDENT_ID = 10L;

    private final Long EXPECTED_STUDENT_ID = 1L;
    private final int EXPECTED_NUMBER_OF_INVOCATIONS = 1;
    private final int UNEXPECTED_NUMBER_OF_INVOCATIONS = 0;

    //endregion

    //region Properties Under Test
    @Mock
    private S4SystemDao systemDao;

    @Autowired
    @InjectMocks
    private S4SystemService<StudentResponseDto, Student, Class> studentService;
    //endregion

    //region Test Setup
    @Before
    public void setUp() throws Exception {
        Student toBeReturnedFromDao = new Student(STUDENT_ID, STUDENT_LAST_NAME, STUDENT_NAME);
        when(systemDao.load(Student.class, STUDENT_ID)).thenReturn(toBeReturnedFromDao);
    }

    @After
    public void tearDown() throws Exception {
    }
    //endregion

    //region UnitTests for S4SystemService
    @Test
    public void testFind_ReturnedValueShouldBe_DtoClass() {

        StudentResponseDto actualResult = studentService.find(StudentResponseDto.class, Student.class, STUDENT_ID);

        assertNotNull(actualResult);
        assertEquals(EXPECTED_STUDENT_ID, actualResult.getId());
    }

    @Test
    public void testFind_DaoLoadMethod_WithCorrectParameters() {

        //Verifying that the persistence layer calls LOAD method
        studentService.find(StudentResponseDto.class, Student.class, STUDENT_ID);
        verify(systemDao).load(Student.class, STUDENT_ID);
    }

    @Test
    public void testFind_DaoLoadMethod_ExecutedOnce() {

        studentService.find(StudentResponseDto.class, Student.class, STUDENT_ID);
        verify(systemDao, Mockito.times(EXPECTED_NUMBER_OF_INVOCATIONS)).load(Student.class, STUDENT_ID);
    }

    @Test
    public void testFind_DaoLoadMethod_WithWrongParameters() {

        studentService.find(StudentResponseDto.class, Student.class, STUDENT_ID);
        verify(systemDao, Mockito.never()).load(Student.class, NON_EXISTING_STUDENT_ID);
        verify(systemDao, Mockito.times(UNEXPECTED_NUMBER_OF_INVOCATIONS)).load(Student.class, NON_EXISTING_STUDENT_ID);
    }

    @Test
    public void testFind_DaoLoadMethod_NonExecutedMoreThanOnce() {

        studentService.find(StudentResponseDto.class, Student.class, STUDENT_ID);
        verify(systemDao, Mockito.atMost(EXPECTED_NUMBER_OF_INVOCATIONS)).load(Student.class, STUDENT_ID);
    }

    @Test
    public void testGetAll_DaoFindMethod_WithCorrectParameter() {
        studentService.getAll(StudentResponseDto.class, Student.class);
        verify(systemDao).find(Student.class);
    }

    @Test
    public void testGetAll_DaoFindMethod_WithWrongParameter() {
        studentService.getAll(StudentResponseDto.class, Student.class);
        verify(systemDao, Mockito.never()).find(Class.class);
    }

    @Test
    public void testGetAll_DaoFindMethod_ExecutedOnce() {
        studentService.getAll(StudentResponseDto.class, Student.class);
        verify(systemDao, Mockito.times(EXPECTED_NUMBER_OF_INVOCATIONS)).find(Student.class);
    }

    @Test
    public void testSave_DaoPersistMethod_WithCorrectParameters() {
        //TODO: Complete unit tests
    }

    @Test
    public void testSave_DaoPersistMethod_ExecutedOnce() {
        //TODO: Complete unit tests
    }
    //endregion
}