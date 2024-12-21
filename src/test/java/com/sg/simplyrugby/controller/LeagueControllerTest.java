package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.model.simply.League;
import com.sg.simplyrugby.service.LeagueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LeagueControllerTest {

    @Mock
    private LeagueService mockLeagueService;

    @InjectMocks
    private LeagueController leagueControllerUnderTest;

    @Test
    public void testGetLeague() {
        // Setup
        final League expectedResult = new League();
        expectedResult.setId("1133cc38-ec33-4a91-a6a8-e0c78a9efaa9");
        expectedResult.setName("name");
        expectedResult.setCountry("country");
        expectedResult.setLevel("level");
        expectedResult.setSportType("sportType");

        // Configure LeagueService.getById(...).
        final League league = new League();
        league.setId("1133cc38-ec33-4a91-a6a8-e0c78a9efaa9");
        league.setName("name");
        league.setCountry("country");
        league.setLevel("level");
        league.setSportType("sportType");
        when(mockLeagueService.getById("id")).thenReturn(league);

        // Run the test
        final League result = leagueControllerUnderTest.getLeague("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCreateLeague() {
        // Setup
        final League league = new League();
        league.setId("1133cc38-ec33-4a91-a6a8-e0c78a9efaa9");
        league.setName("name");
        league.setCountry("country");
        league.setLevel("level");
        league.setSportType("sportType");

        final AjaxResult expectedResult = new AjaxResult();

        // Configure LeagueService.save(...).
        final League entity = new League();
        entity.setId("1133cc38-ec33-4a91-a6a8-e0c78a9efaa9");
        entity.setName("name");
        entity.setCountry("country");
        entity.setLevel("level");
        entity.setSportType("sportType");
        when(mockLeagueService.save(entity)).thenReturn(false);

        // Run the test
        final AjaxResult result = leagueControllerUnderTest.createLeague(league);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdateLeague() {
        // Setup
        final League league = new League();
        league.setId("1133cc38-ec33-4a91-a6a8-e0c78a9efaa9");
        league.setName("name");
        league.setCountry("country");
        league.setLevel("level");
        league.setSportType("sportType");

        final AjaxResult expectedResult = new AjaxResult();

        // Configure LeagueService.updateById(...).
        final League entity = new League();
        entity.setId("1133cc38-ec33-4a91-a6a8-e0c78a9efaa9");
        entity.setName("name");
        entity.setCountry("country");
        entity.setLevel("level");
        entity.setSportType("sportType");
        when(mockLeagueService.updateById(entity)).thenReturn(false);

        // Run the test
        final AjaxResult result = leagueControllerUnderTest.updateLeague("1133cc38-ec33-4a91-a6a8-e0c78a9efaa9",
                league);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteLeague() {
        // Setup
        final AjaxResult expectedResult = new AjaxResult();
        when(mockLeagueService.removeById("id")).thenReturn(false);

        // Run the test
        final AjaxResult result = leagueControllerUnderTest.deleteLeague("id");

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

        // Configure LeagueService.list(...).
        final League league = new League();
        league.setId("1133cc38-ec33-4a91-a6a8-e0c78a9efaa9");
        league.setName("name");
        league.setCountry("country");
        league.setLevel("level");
        league.setSportType("sportType");
        final List<League> leagues = Arrays.asList(league);
        when(mockLeagueService.list(any(LambdaQueryWrapper.class))).thenReturn(leagues);

        // Run the test
        final ResultTable result = leagueControllerUnderTest.page(pageDTO);

        // Verify the results
    }

    @Test
    public void testPage_LeagueServiceReturnsNoItems() {
        // Setup
        final PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(0);
        pageDTO.setLimit(0);
        pageDTO.setSearchText("searchText");

        when(mockLeagueService.list(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final ResultTable result = leagueControllerUnderTest.page(pageDTO);

        // Verify the results
    }

    @Test
    public void testEdit() {
        // Setup
        final ModelMap mmap = new ModelMap("attributeName", "attributeValue");

        // Configure LeagueService.getById(...).
        final League league = new League();
        league.setId("1133cc38-ec33-4a91-a6a8-e0c78a9efaa9");
        league.setName("name");
        league.setCountry("country");
        league.setLevel("level");
        league.setSportType("sportType");
        when(mockLeagueService.getById("id")).thenReturn(league);

        // Run the test
        final String result = leagueControllerUnderTest.edit("id", mmap);

        // Verify the results
        assertEquals("league/edit", result);
    }
}
