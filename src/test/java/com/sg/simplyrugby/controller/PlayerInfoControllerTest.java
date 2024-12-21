package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.dto.PlayerInfoDTO;
import com.sg.simplyrugby.model.simply.PlayerInfo;
import com.sg.simplyrugby.service.PlayerInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerInfoControllerTest {

    @Mock
    private PlayerInfoService mockPlayerInfoService;

    @InjectMocks
    private PlayerInfoController playerInfoControllerUnderTest;

    @Test
    public void testGetPlayerInfo() {
        // Setup
        final PlayerInfoDTO expectedResult = new PlayerInfoDTO();
        expectedResult.setId("id");
        expectedResult.setName("name");
        expectedResult.setCoachIds(Arrays.asList("value"));
        expectedResult.setCoachNames(Arrays.asList("value"));

        // Configure PlayerInfoService.getInfo(...).
        final PlayerInfoDTO dto = new PlayerInfoDTO();
        dto.setId("id");
        dto.setName("name");
        dto.setCoachIds(Arrays.asList("value"));
        dto.setCoachNames(Arrays.asList("value"));
        when(mockPlayerInfoService.getInfo("id")).thenReturn(dto);

        // Run the test
        final PlayerInfoDTO result = playerInfoControllerUnderTest.getPlayerInfo("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCreatePlayerInfo() {
        // Setup
        final PlayerInfoDTO playerInfo = new PlayerInfoDTO();
        playerInfo.setId("id");
        playerInfo.setName("name");
        playerInfo.setCoachIds(Arrays.asList("value"));
        playerInfo.setCoachNames(Arrays.asList("value"));

        final AjaxResult expectedResult = new AjaxResult();

        // Configure PlayerInfoService.savePlayer(...).
        final PlayerInfoDTO playerInfo1 = new PlayerInfoDTO();
        playerInfo1.setId("id");
        playerInfo1.setName("name");
        playerInfo1.setCoachIds(Arrays.asList("value"));
        playerInfo1.setCoachNames(Arrays.asList("value"));
        when(mockPlayerInfoService.savePlayer(playerInfo1)).thenReturn(false);

        // Run the test
        final AjaxResult result = playerInfoControllerUnderTest.createPlayerInfo(playerInfo);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdatePlayerInfo() {
        // Setup
        final PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setId("id");
        playerInfo.setName("name");
        playerInfo.setAge(0);
        playerInfo.setHeight(new BigDecimal("0.00"));
        playerInfo.setWeight(new BigDecimal("0.00"));

        final AjaxResult expectedResult = new AjaxResult();

        // Configure PlayerInfoService.updateById(...).
        final PlayerInfo entity = new PlayerInfo();
        entity.setId("id");
        entity.setName("name");
        entity.setAge(0);
        entity.setHeight(new BigDecimal("0.00"));
        entity.setWeight(new BigDecimal("0.00"));
        when(mockPlayerInfoService.updateById(entity)).thenReturn(false);

        // Run the test
        final AjaxResult result = playerInfoControllerUnderTest.updatePlayerInfo("id", playerInfo);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testDeletePlayerInfo() {
        // Setup
        final AjaxResult expectedResult = new AjaxResult();
        when(mockPlayerInfoService.removeBatchByIds(Arrays.asList("value"))).thenReturn(false);
        when(mockPlayerInfoService.removeById("id")).thenReturn(false);

        // Run the test
        final AjaxResult result = playerInfoControllerUnderTest.deletePlayerInfo("id");

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

        // Configure PlayerInfoService.list(...).
        final PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setId("id");
        playerInfo.setName("name");
        playerInfo.setAge(0);
        playerInfo.setHeight(new BigDecimal("0.00"));
        playerInfo.setWeight(new BigDecimal("0.00"));
        final List<PlayerInfo> playerInfos = Arrays.asList(playerInfo);
        when(mockPlayerInfoService.list(any(LambdaQueryWrapper.class))).thenReturn(playerInfos);

        // Run the test
        final ResultTable result = playerInfoControllerUnderTest.page(pageDTO);

        // Verify the results
    }

    @Test
    public void testPage_PlayerInfoServiceReturnsNoItems() {
        // Setup
        final PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(0);
        pageDTO.setLimit(0);
        pageDTO.setSearchText("searchText");

        when(mockPlayerInfoService.list(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final ResultTable result = playerInfoControllerUnderTest.page(pageDTO);

        // Verify the results
    }

    @Test
    public void testEdit() {
        // Setup
        final ModelMap mmap = new ModelMap("attributeName", "attributeValue");

        // Configure PlayerInfoService.getInfo(...).
        final PlayerInfoDTO dto = new PlayerInfoDTO();
        dto.setId("id");
        dto.setName("name");
        dto.setCoachIds(Arrays.asList("value"));
        dto.setCoachNames(Arrays.asList("value"));
        when(mockPlayerInfoService.getInfo("id")).thenReturn(dto);

        // Run the test
        final String result = playerInfoControllerUnderTest.edit("id", mmap);

        // Verify the results
        assertEquals("player/edit", result);
    }
}
