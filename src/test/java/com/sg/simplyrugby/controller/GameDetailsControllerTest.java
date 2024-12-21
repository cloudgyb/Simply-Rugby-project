package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.model.simply.GameDetails;
import com.sg.simplyrugby.service.GameDetailsService;
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
public class GameDetailsControllerTest {

    @Mock
    private GameDetailsService mockGameDetailsService;

    @InjectMocks
    private GameDetailsController gameDetailsControllerUnderTest;

    @Test
    public void testAjaxResult() {
        // Setup
        final GameDetails gameDetails = new GameDetails();
        gameDetails.setId("id");
        gameDetails.setGameName("gameName");
        gameDetails.setHomeTeamId("homeTeamId");
        gameDetails.setAwayTeamId("awayTeamId");
        gameDetails.setDateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final AjaxResult expectedResult = new AjaxResult();

        // Configure GameDetailsService.saveOrUpdate(...).
        final GameDetails entity = new GameDetails();
        entity.setId("id");
        entity.setGameName("gameName");
        entity.setHomeTeamId("homeTeamId");
        entity.setAwayTeamId("awayTeamId");
        entity.setDateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockGameDetailsService.saveOrUpdate(entity)).thenReturn(false);

        // Run the test
        final AjaxResult result = gameDetailsControllerUnderTest.AjaxResult(gameDetails);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteById() {
        // Setup
        final AjaxResult expectedResult = new AjaxResult();
        when(mockGameDetailsService.removeById("id")).thenReturn(false);

        // Run the test
        final AjaxResult result = gameDetailsControllerUnderTest.deleteById("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdate() {
        // Setup
        final GameDetails gameDetails = new GameDetails();
        gameDetails.setId("id");
        gameDetails.setGameName("gameName");
        gameDetails.setHomeTeamId("homeTeamId");
        gameDetails.setAwayTeamId("awayTeamId");
        gameDetails.setDateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final AjaxResult expectedResult = new AjaxResult();

        // Configure GameDetailsService.updateById(...).
        final GameDetails entity = new GameDetails();
        entity.setId("id");
        entity.setGameName("gameName");
        entity.setHomeTeamId("homeTeamId");
        entity.setAwayTeamId("awayTeamId");
        entity.setDateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockGameDetailsService.updateById(entity)).thenReturn(false);

        // Run the test
        final AjaxResult result = gameDetailsControllerUnderTest.update(gameDetails);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetById() {
        // Setup
        final GameDetails expectedResult = new GameDetails();
        expectedResult.setId("id");
        expectedResult.setGameName("gameName");
        expectedResult.setHomeTeamId("homeTeamId");
        expectedResult.setAwayTeamId("awayTeamId");
        expectedResult.setDateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Configure GameDetailsService.getById(...).
        final GameDetails gameDetails = new GameDetails();
        gameDetails.setId("id");
        gameDetails.setGameName("gameName");
        gameDetails.setHomeTeamId("homeTeamId");
        gameDetails.setAwayTeamId("awayTeamId");
        gameDetails.setDateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockGameDetailsService.getById("id")).thenReturn(gameDetails);

        // Run the test
        final GameDetails result = gameDetailsControllerUnderTest.getById("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testList() {
        // Setup
        final GameDetails gameDetails = new GameDetails();
        gameDetails.setId("id");
        gameDetails.setGameName("gameName");
        gameDetails.setHomeTeamId("homeTeamId");
        gameDetails.setAwayTeamId("awayTeamId");
        gameDetails.setDateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<GameDetails> expectedResult = Arrays.asList(gameDetails);

        // Configure GameDetailsService.list(...).
        final GameDetails gameDetails2 = new GameDetails();
        gameDetails2.setId("id");
        gameDetails2.setGameName("gameName");
        gameDetails2.setHomeTeamId("homeTeamId");
        gameDetails2.setAwayTeamId("awayTeamId");
        gameDetails2.setDateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<GameDetails> gameDetails1 = Arrays.asList(gameDetails2);
        when(mockGameDetailsService.list()).thenReturn(gameDetails1);

        // Run the test
        final List<GameDetails> result = gameDetailsControllerUnderTest.list();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testList_GameDetailsServiceReturnsNoItems() {
        // Setup
        when(mockGameDetailsService.list()).thenReturn(Collections.emptyList());

        // Run the test
        final List<GameDetails> result = gameDetailsControllerUnderTest.list();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testPage() {
        // Setup
        final PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(0);
        pageDTO.setLimit(0);
        pageDTO.setSearchText("searchText");

        // Configure GameDetailsService.list(...).
        final GameDetails gameDetails1 = new GameDetails();
        gameDetails1.setId("id");
        gameDetails1.setGameName("gameName");
        gameDetails1.setHomeTeamId("homeTeamId");
        gameDetails1.setAwayTeamId("awayTeamId");
        gameDetails1.setDateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<GameDetails> gameDetails = Arrays.asList(gameDetails1);
        when(mockGameDetailsService.list(any(LambdaQueryWrapper.class))).thenReturn(gameDetails);

        // Run the test
        final ResultTable result = gameDetailsControllerUnderTest.page(pageDTO);

        // Verify the results
    }

    @Test
    public void testPage_GameDetailsServiceReturnsNoItems() {
        // Setup
        final PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(0);
        pageDTO.setLimit(0);
        pageDTO.setSearchText("searchText");

        when(mockGameDetailsService.list(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final ResultTable result = gameDetailsControllerUnderTest.page(pageDTO);

        // Verify the results
    }

    @Test
    public void testEdit() {
        // Setup
        final ModelMap mmap = new ModelMap("attributeName", "attributeValue");

        // Configure GameDetailsService.getById(...).
        final GameDetails gameDetails = new GameDetails();
        gameDetails.setId("id");
        gameDetails.setGameName("gameName");
        gameDetails.setHomeTeamId("homeTeamId");
        gameDetails.setAwayTeamId("awayTeamId");
        gameDetails.setDateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockGameDetailsService.getById("id")).thenReturn(gameDetails);

        // Run the test
        final String result = gameDetailsControllerUnderTest.edit("id", mmap);

        // Verify the results
        assertEquals("gameDetails/edit", result);
    }
}
