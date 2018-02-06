package com.fral.extreme.s4.services;

import com.fral.extreme.s4.common.dto.response.ClassResponseDto;
import com.fral.extreme.s4.domain.model.Class;
import com.fral.extreme.s4.domain.model.Student;
import com.fral.extreme.s4.domain.repository.S4SystemDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClassServiceTest {

    @Mock
    private S4SystemDao systemDao;

    @Autowired
    @InjectMocks
    private ClassService classService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFind_ReturnedValueShouldBe_DtoClass() {
        Class toBeReturnedFromDao = new Class("XXXX", "Spring Framework", "Spring Framework Master Class");
        toBeReturnedFromDao.setId(1L);

        when(systemDao.load(Class.class, 1L)).thenReturn(toBeReturnedFromDao);

        ClassResponseDto actualResult = classService.find(1L);
        Long expectedId = 1L;

        assertNotNull(actualResult);
        assertEquals(expectedId, actualResult.getId());
    }

    @Test
    public void testFind_DaoLoadMethod_WithCorrectParameters() {

        classService.find(1L);
        verify(systemDao).load(Class.class, 1L);
    }

    @Test
    public void testFind_DaoLoadMethod_ExecutedOnce() {

        classService.find(1L);
        verify(systemDao, Mockito.times(1)).load(Class.class, 1L);
    }

    @Test
    public void testFind_DaoLoadMethod_WithWrongParameters() {

        classService.find(1L);
        verify(systemDao, Mockito.never()).load(Class.class, 10L);
        verify(systemDao, Mockito.times(0)).load(Class.class, 10L);
    }

    @Test
    public void testFind_DaoLoadMethod_NonExecutedMoreThanOnce() {

        classService.find(1L);
        verify(systemDao, Mockito.atMost(1)).load(Class.class, 1L);
    }

    @Test
    public void testGetAll_DaoFindMethod_WithCorrectParameter() {
        classService.getAll();
        verify(systemDao).find(Class.class);
    }

    @Test
    public void testGetAll_DaoFindMethod_WithWrongParameter() {
        classService.getAll();
        verify(systemDao, Mockito.never()).find(Student.class);
    }

    @Test
    public void testGetAll_DaoFindMethod_ExecutedOnce() {
        classService.getAll();
        verify(systemDao, Mockito.times(1)).find(Class.class);
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getClassesAssignedToStudent() {
    }

    @Test
    public void findByProperty() {
    }

    @Test
    public void registerNewStudent() {
    }

    @Test
    public void registerStudents() {
    }
}