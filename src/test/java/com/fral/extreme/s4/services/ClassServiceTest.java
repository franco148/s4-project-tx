package com.fral.extreme.s4.services;

import com.fral.extreme.s4.common.dto.response.ClassResponseDto;
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
public class ClassServiceTest {

    //region PrivateProperties
    private final Long CLASS_ID = 1L;
    private final String CLASS_CODE = "SC";
    private final String CLASS_TITLE = "ST";
    private final String CLASS_DESCRIPTION = "SD";
    private final Long NON_EXISTING_CLASS_ID = 10L;

    private final Long EXPECTED_CLASS_ID = 1L;
    private final int EXPECTED_NUMBER_OF_INVOCATIONS = 1;
    private final int UNEXPECTED_NUMBER_OF_INVOCATIONS = 0;

    //endregion

    //region Properties Under Test
    @Mock
    private S4SystemDao systemDao;

    @Autowired
    @InjectMocks
    private S4SystemService<ClassResponseDto, Class, Student> classService;
    //endregion

    //region Test Setup
    @Before
    public void setUp() {
        Class toBeReturnedFromDao = new Class(CLASS_ID, CLASS_CODE, CLASS_TITLE, CLASS_DESCRIPTION);
        when(systemDao.load(Class.class, CLASS_ID)).thenReturn(toBeReturnedFromDao);
    }

    @After
    public void tearDown() {
    }
    //endregion

    //region UnitTests for S4SystemService
    @Test
    public void testFind_ReturnedValueShouldBe_DtoClass() {

        ClassResponseDto actualResult = classService.find(ClassResponseDto.class, Class.class, CLASS_ID);

        assertNotNull(actualResult);
        assertEquals(EXPECTED_CLASS_ID, actualResult.getId());
    }

    @Test
    public void testFind_DaoLoadMethod_WithCorrectParameters() {

        classService.find(ClassResponseDto.class, Class.class, CLASS_ID);

        verify(systemDao).load(Class.class, CLASS_ID);
    }

    @Test
    public void testFind_DaoLoadMethod_ExecutedOnce() {

        classService.find(ClassResponseDto.class, Class.class, CLASS_ID);
        verify(systemDao, Mockito.times(EXPECTED_NUMBER_OF_INVOCATIONS)).load(Class.class, CLASS_ID);
    }

    @Test
    public void testFind_DaoLoadMethod_WithWrongParameters() {

        classService.find(ClassResponseDto.class, Class.class, CLASS_ID);
        verify(systemDao, Mockito.never()).load(Class.class, NON_EXISTING_CLASS_ID);
        verify(systemDao, Mockito.times(UNEXPECTED_NUMBER_OF_INVOCATIONS)).load(Class.class, NON_EXISTING_CLASS_ID);
    }

    @Test
    public void testFind_DaoLoadMethod_NonExecutedMoreThanOnce() {

        classService.find(ClassResponseDto.class, Class.class, CLASS_ID);
        verify(systemDao, Mockito.atMost(EXPECTED_NUMBER_OF_INVOCATIONS)).load(Class.class, CLASS_ID);
    }

    @Test
    public void testGetAll_DaoFindMethod_WithCorrectParameter() {
        classService.getAll(ClassResponseDto.class, Class.class);
        verify(systemDao).find(Class.class);
    }

    @Test
    public void testGetAll_DaoFindMethod_WithWrongParameter() {
        classService.getAll(ClassResponseDto.class, Class.class);
        verify(systemDao, Mockito.never()).find(Student.class);
    }

    @Test
    public void testGetAll_DaoFindMethod_ExecutedOnce() {
        classService.getAll(ClassResponseDto.class, Class.class);
        verify(systemDao, Mockito.times(EXPECTED_NUMBER_OF_INVOCATIONS)).find(Class.class);
    }
    //endregion
}