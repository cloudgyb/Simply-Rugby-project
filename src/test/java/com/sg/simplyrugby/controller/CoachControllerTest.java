package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.model.simply.Coach;
import com.sg.simplyrugby.service.CoachService;
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
public class CoachControllerTest {

    @Mock
    private CoachService mockCoachService;

    @InjectMocks
    private CoachController coachControllerUnderTest;

    @Test
    public void testGetCoach() {
        // Setup
        final Coach expectedResult = new Coach();
        expectedResult.setId("9cf0e8ec-d4b5-409c-9882-f4491c8ebc6e");
        expectedResult.setFullName("fullName");
        expectedResult.setNationality("nationality");
        expectedResult.setDateOfBirth(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setAge(0);

        // Configure CoachService.getById(...).
        final Coach coach = new Coach();
        coach.setId("9cf0e8ec-d4b5-409c-9882-f4491c8ebc6e");
        coach.setFullName("fullName");
        coach.setNationality("nationality");
        coach.setDateOfBirth(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        coach.setAge(0);
        when(mockCoachService.getById("id")).thenReturn(coach);

        // Run the test
        final Coach result = coachControllerUnderTest.getCoach("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCreateCoach() {
        // Setup
        final Coach coach = new Coach();
        coach.setId("9cf0e8ec-d4b5-409c-9882-f4491c8ebc6e");
        coach.setFullName("fullName");
        coach.setNationality("nationality");
        coach.setDateOfBirth(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        coach.setAge(0);

        final AjaxResult expectedResult = new AjaxResult();

        // Configure CoachService.save(...).
        final Coach entity = new Coach();
        entity.setId("9cf0e8ec-d4b5-409c-9882-f4491c8ebc6e");
        entity.setFullName("fullName");
        entity.setNationality("nationality");
        entity.setDateOfBirth(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setAge(0);
        when(mockCoachService.save(entity)).thenReturn(false);

        // Run the test
        final AjaxResult result = coachControllerUnderTest.createCoach(coach);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdateCoach() {
        // Setup
        final Coach coach = new Coach();
        coach.setId("9cf0e8ec-d4b5-409c-9882-f4491c8ebc6e");
        coach.setFullName("fullName");
        coach.setNationality("nationality");
        coach.setDateOfBirth(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        coach.setAge(0);

        final AjaxResult expectedResult = new AjaxResult();

        // Configure CoachService.updateById(...).
        final Coach entity = new Coach();
        entity.setId("9cf0e8ec-d4b5-409c-9882-f4491c8ebc6e");
        entity.setFullName("fullName");
        entity.setNationality("nationality");
        entity.setDateOfBirth(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        entity.setAge(0);
        when(mockCoachService.updateById(entity)).thenReturn(false);

        // Run the test
        final AjaxResult result = coachControllerUnderTest.updateCoach("9cf0e8ec-d4b5-409c-9882-f4491c8ebc6e", coach);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteCoach() {
        // Setup
        final AjaxResult expectedResult = new AjaxResult();
        when(mockCoachService.removeBatchByIds(Arrays.asList("value"))).thenReturn(false);
        when(mockCoachService.removeById("id")).thenReturn(false);

        // Run the test
        final AjaxResult result = coachControllerUnderTest.deleteCoach("id");

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

        // Configure CoachService.list(...).
        final Coach coach = new Coach();
        coach.setId("9cf0e8ec-d4b5-409c-9882-f4491c8ebc6e");
        coach.setFullName("fullName");
        coach.setNationality("nationality");
        coach.setDateOfBirth(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        coach.setAge(0);
        final List<Coach> coaches = Arrays.asList(coach);
        when(mockCoachService.list(any(LambdaQueryWrapper.class))).thenReturn(coaches);

        // Run the test
        final ResultTable result = coachControllerUnderTest.page(pageDTO);

        // Verify the results
    }

    @Test
    public void testPage_CoachServiceReturnsNoItems() {
        // Setup
        final PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(0);
        pageDTO.setLimit(0);
        pageDTO.setSearchText("searchText");

        when(mockCoachService.list(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final ResultTable result = coachControllerUnderTest.page(pageDTO);

        // Verify the results
    }

    @Test
    public void testEdit() {
        // Setup
        final ModelMap mmap = new ModelMap("attributeName", "attributeValue");

        // Configure CoachService.getById(...).
        final Coach coach = new Coach();
        coach.setId("9cf0e8ec-d4b5-409c-9882-f4491c8ebc6e");
        coach.setFullName("fullName");
        coach.setNationality("nationality");
        coach.setDateOfBirth(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        coach.setAge(0);
        when(mockCoachService.getById("id")).thenReturn(coach);

        // Run the test
        final String result = coachControllerUnderTest.edit("id", mmap);

        // Verify the results
        assertEquals("coach/edit", result);
    }
}
