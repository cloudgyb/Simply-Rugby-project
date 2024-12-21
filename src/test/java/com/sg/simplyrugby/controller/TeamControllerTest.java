package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.model.simply.Team;
import com.sg.simplyrugby.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeamControllerTest {

    @Mock
    private TeamService mockTeamService;

    @InjectMocks
    private TeamController teamControllerUnderTest;

    @Test
    public void testGetTeam() {
        // Setup
        final Team expectedResult = new Team();
        expectedResult.setId("ef3e6b87-2fcf-4b97-a782-ebbd9a25c5f5");
        expectedResult.setName("name");
        expectedResult.setNickname("nickname");
        expectedResult.setFoundationYear(new Date());
        expectedResult.setLeagueId("leagueId");

        // Configure TeamService.getById(...).
        final Team team = new Team();
        team.setId("ef3e6b87-2fcf-4b97-a782-ebbd9a25c5f5");
        team.setName("name");
        team.setNickname("nickname");
        team.setFoundationYear(new Date());
        team.setLeagueId("leagueId");
        when(mockTeamService.getById("id")).thenReturn(team);

        // Run the test
        final Team result = teamControllerUnderTest.getTeam("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCreateTeam() {
        // Setup
        final Team team = new Team();
        team.setId("ef3e6b87-2fcf-4b97-a782-ebbd9a25c5f5");
        team.setName("name");
        team.setNickname("nickname");
        team.setFoundationYear(new Date());
        team.setLeagueId("leagueId");

        final AjaxResult expectedResult = new AjaxResult();

        // Configure TeamService.save(...).
        final Team entity = new Team();
        entity.setId("ef3e6b87-2fcf-4b97-a782-ebbd9a25c5f5");
        entity.setName("name");
        entity.setNickname("nickname");
        entity.setFoundationYear(new Date());
        entity.setLeagueId("leagueId");
        when(mockTeamService.save(entity)).thenReturn(false);

        // Run the test
        final AjaxResult result = teamControllerUnderTest.createTeam(team);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdateTeam() {
        // Setup
        final Team team = new Team();
        team.setId("ef3e6b87-2fcf-4b97-a782-ebbd9a25c5f5");
        team.setName("name");
        team.setNickname("nickname");
        team.setFoundationYear(new Date());
        team.setLeagueId("leagueId");

        final AjaxResult expectedResult = new AjaxResult();

        // Configure TeamService.updateById(...).
        final Team entity = new Team();
        entity.setId("ef3e6b87-2fcf-4b97-a782-ebbd9a25c5f5");
        entity.setName("name");
        entity.setNickname("nickname");
        entity.setFoundationYear(new Date());
        entity.setLeagueId("leagueId");
        when(mockTeamService.updateById(entity)).thenReturn(false);

        // Run the test
        final AjaxResult result = teamControllerUnderTest.updateTeam("ef3e6b87-2fcf-4b97-a782-ebbd9a25c5f5", team);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeleteTeam() {
        // Setup
        final AjaxResult expectedResult = new AjaxResult();
        when(mockTeamService.removeById("id")).thenReturn(false);

        // Run the test
        final AjaxResult result = teamControllerUnderTest.deleteTeam("id");

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

        // Configure TeamService.list(...).
        final Team team = new Team();
        team.setId("ef3e6b87-2fcf-4b97-a782-ebbd9a25c5f5");
        team.setName("name");
        team.setNickname("nickname");
        team.setFoundationYear(new Date());
        team.setLeagueId("leagueId");
        final List<Team> teams = Arrays.asList(team);
        when(mockTeamService.list(any(LambdaQueryWrapper.class))).thenReturn(teams);

        // Run the test
        final ResultTable result = teamControllerUnderTest.page(pageDTO);

        // Verify the results
    }

    @Test
    public void testPage_TeamServiceReturnsNoItems() {
        // Setup
        final PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(0);
        pageDTO.setLimit(0);
        pageDTO.setSearchText("searchText");

        when(mockTeamService.list(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final ResultTable result = teamControllerUnderTest.page(pageDTO);

        // Verify the results
    }

    @Test
    public void testEdit() {
        // Setup
        final ModelMap mmap = new ModelMap("attributeName", "attributeValue");

        // Configure TeamService.getById(...).
        final Team team = new Team();
        team.setId("ef3e6b87-2fcf-4b97-a782-ebbd9a25c5f5");
        team.setName("name");
        team.setNickname("nickname");
        team.setFoundationYear(new Date());
        team.setLeagueId("leagueId");
        when(mockTeamService.getById("id")).thenReturn(team);

        // Run the test
        final String result = teamControllerUnderTest.edit("id", mmap);

        // Verify the results
        assertEquals("team/edit", result);
    }
}
