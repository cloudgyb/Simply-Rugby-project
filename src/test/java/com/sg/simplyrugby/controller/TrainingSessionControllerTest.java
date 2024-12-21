package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.model.simply.TrainingSession;
import com.sg.simplyrugby.service.TrainingSessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrainingSessionControllerTest {

    @Mock
    private TrainingSessionService mockTrainingSessionService;

    @InjectMocks
    private TrainingSessionController trainingSessionControllerUnderTest;

    @Test
    public void testSave() {
        // Setup
        final TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId("7089bb00-a76d-4dfa-bca1-67a381131a92");
        trainingSession.setName("name");
        trainingSession.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        trainingSession.setTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        trainingSession.setCoachId("coachId");

        final AjaxResult expectedResult = new AjaxResult();

        // Configure TrainingSessionService.saveOrUpdate(...).
        final TrainingSession entity = new TrainingSession();
        entity.setId("7089bb00-a76d-4dfa-bca1-67a381131a92");
        entity.setName("name");
        entity.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setCoachId("coachId");
        when(mockTrainingSessionService.saveOrUpdate(entity)).thenReturn(false);

        // Run the test
        final AjaxResult result = trainingSessionControllerUnderTest.save(trainingSession);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDelete() {
        // Setup
        final AjaxResult expectedResult = new AjaxResult();
        when(mockTrainingSessionService.removeById("id")).thenReturn(false);

        // Run the test
        final AjaxResult result = trainingSessionControllerUnderTest.delete("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindAll() {
        // Setup
        final TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId("7089bb00-a76d-4dfa-bca1-67a381131a92");
        trainingSession.setName("name");
        trainingSession.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        trainingSession.setTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        trainingSession.setCoachId("coachId");
        final List<TrainingSession> expectedResult = Arrays.asList(trainingSession);

        // Configure TrainingSessionService.list(...).
        final TrainingSession trainingSession1 = new TrainingSession();
        trainingSession1.setId("7089bb00-a76d-4dfa-bca1-67a381131a92");
        trainingSession1.setName("name");
        trainingSession1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        trainingSession1.setTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        trainingSession1.setCoachId("coachId");
        final List<TrainingSession> trainingSessions = Arrays.asList(trainingSession1);
        when(mockTrainingSessionService.list()).thenReturn(trainingSessions);

        // Run the test
        final List<TrainingSession> result = trainingSessionControllerUnderTest.findAll();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindAll_TrainingSessionServiceReturnsNoItems() {
        // Setup
        when(mockTrainingSessionService.list()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TrainingSession> result = trainingSessionControllerUnderTest.findAll();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testFindById() {
        // Setup
        final TrainingSession expectedResult = new TrainingSession();
        expectedResult.setId("7089bb00-a76d-4dfa-bca1-67a381131a92");
        expectedResult.setName("name");
        expectedResult.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setCoachId("coachId");

        // Configure TrainingSessionService.getById(...).
        final TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId("7089bb00-a76d-4dfa-bca1-67a381131a92");
        trainingSession.setName("name");
        trainingSession.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        trainingSession.setTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        trainingSession.setCoachId("coachId");
        when(mockTrainingSessionService.getById("id")).thenReturn(trainingSession);

        // Run the test
        final TrainingSession result = trainingSessionControllerUnderTest.findById("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testPage() {
        // Setup
        final PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(0);
        pageDTO.setLimit(0);
        pageDTO.setSearchText("searchText");

        // Configure TrainingSessionService.list(...).
        final TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId("7089bb00-a76d-4dfa-bca1-67a381131a92");
        trainingSession.setName("name");
        trainingSession.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        trainingSession.setTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        trainingSession.setCoachId("coachId");
        final List<TrainingSession> trainingSessions = Arrays.asList(trainingSession);
        when(mockTrainingSessionService.list(any(LambdaQueryWrapper.class))).thenReturn(trainingSessions);

        // Run the test
        final ResultTable result = trainingSessionControllerUnderTest.page(pageDTO);

        // Verify the results
    }

    @Test
    public void testPage_TrainingSessionServiceReturnsNoItems() {
        // Setup
        final PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(0);
        pageDTO.setLimit(0);
        pageDTO.setSearchText("searchText");

        when(mockTrainingSessionService.list(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final ResultTable result = trainingSessionControllerUnderTest.page(pageDTO);

        // Verify the results
    }

    @Test
    public void testEdit() {
        // Setup
        final ModelMap mmap = new ModelMap("attributeName", "attributeValue");

        // Configure TrainingSessionService.getById(...).
        final TrainingSession trainingSession = new TrainingSession();
        trainingSession.setId("7089bb00-a76d-4dfa-bca1-67a381131a92");
        trainingSession.setName("name");
        trainingSession.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        trainingSession.setTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        trainingSession.setCoachId("coachId");
        when(mockTrainingSessionService.getById("id")).thenReturn(trainingSession);

        // Run the test
        final String result = trainingSessionControllerUnderTest.edit("id", mmap);

        // Verify the results
        assertEquals("trainingSession/edit", result);
    }
}
