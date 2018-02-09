package com.fral.extreme.s4.services;

import com.fral.extreme.s4.common.dto.response.ClassResponseDto;
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
public class ClassServiceTest {

    @Mock
    private S4SystemDao systemDao;

    @Autowired
    @InjectMocks
    private ClassService classService;

    @Before
    public void setUp() throws Exception {
        Class toBeReturnedFromDao = new Class(1L, "SC", "ST", "SD");
        when(systemDao.load(Class.class, 1L)).thenReturn(toBeReturnedFromDao);
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void testFind_ReturnedValueShouldBe_DtoClass() throws EntityNotFoundException {
//        Class toBeReturnedFromDao = new Class("XXXX", "Spring Framework", "Spring Framework Master Class");
//        toBeReturnedFromDao.setId(1L);
//
//        when(systemDao.load(Class.class, 1L)).thenReturn(toBeReturnedFromDao);
//
//        ClassResponseDto actualResult = classService.find(1L);
//        Long expectedId = 1L;
//
//        assertNotNull(actualResult);
//        assertEquals(expectedId, actualResult.getId());
//    }
//
//    @Test
//    public void testFind_DaoLoadMethod_WithCorrectParameters() throws EntityNotFoundException {
//
//        classService.find(1L);
//        verify(systemDao).load(Class.class, 1L);
//    }
//
//    @Test
//    public void testFind_DaoLoadMethod_ExecutedOnce() throws EntityNotFoundException {
//
//        classService.find(1L);
//        verify(systemDao, Mockito.times(1)).load(Class.class, 1L);
//    }
//
//    @Test
//    public void testFind_DaoLoadMethod_WithWrongParameters() throws EntityNotFoundException {
//
//        classService.find(1L);
//        verify(systemDao, Mockito.never()).load(Class.class, 10L);
//        verify(systemDao, Mockito.times(0)).load(Class.class, 10L);
//    }
//
//    @Test
//    public void testFind_DaoLoadMethod_NonExecutedMoreThanOnce() throws EntityNotFoundException {
//
//        classService.find(1L);
//        verify(systemDao, Mockito.atMost(1)).load(Class.class, 1L);
//    }
//
//    @Test
//    public void testGetAll_DaoFindMethod_WithCorrectParameter() {
//        classService.getAll();
//        verify(systemDao).find(Class.class);
//    }
//
//    @Test
//    public void testGetAll_DaoFindMethod_WithWrongParameter() {
//        classService.getAll();
//        verify(systemDao, Mockito.never()).find(Student.class);
//    }
//
//    @Test
//    public void testGetAll_DaoFindMethod_ExecutedOnce() {
//        classService.getAll();
//        verify(systemDao, Mockito.times(1)).find(Class.class);
//    }
}