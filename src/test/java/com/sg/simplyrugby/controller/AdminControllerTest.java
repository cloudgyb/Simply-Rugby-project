package com.sg.simplyrugby.controller;

import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.model.SysMenu;
import com.sg.simplyrugby.model.SysUser;
import com.sg.simplyrugby.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

    @Mock
    private SysUserService mockTsysUserDao;

    @InjectMocks
    private AdminController adminControllerUnderTest;

    @Test
    public void testIndex() {
        // Setup
        final MockHttpServletRequest request = new MockHttpServletRequest();

        // Run the test
        final String result = adminControllerUnderTest.index(request);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetUserMenu() {
        // Setup
        final List<SysMenu> expectedResult = Arrays.asList(
                new SysMenu("id", "parentId", "title", 0, 0, "icon", "href", Arrays.asList(), "checkArr"));

        // Run the test
        final List<SysMenu> result = adminControllerUnderTest.getUserMenu();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testLogin1() {
        // Setup
        final ModelMap modelMap = new ModelMap("attributeName", "attributeValue");

        // Run the test
        final String result = adminControllerUnderTest.login(modelMap);

        // Verify the results
        assertEquals("login", result);
    }

    @Test
    public void testLogin2() {
        // Setup
        final SysUser user = new SysUser();
        user.setId("id");
        user.setUsername("username");
        user.setPassword("password");
        user.setNickname("nickname");
        user.setDepId(0);

        final MockHttpServletRequest request = new MockHttpServletRequest();
        final AjaxResult expectedResult = new AjaxResult();

        // Configure SysUserService.queryUserName(...).
        final SysUser sysUser = new SysUser();
        sysUser.setId("id");
        sysUser.setUsername("username");
        sysUser.setPassword("password");
        sysUser.setNickname("nickname");
        sysUser.setDepId(0);
        when(mockTsysUserDao.queryUserName("username")).thenReturn(sysUser);

        // Run the test
        final AjaxResult result = adminControllerUnderTest.login(user, "captcha", null, false, request);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testLogin2_SysUserServiceReturnsNull() {
        // Setup
        final SysUser user = new SysUser();
        user.setId("id");
        user.setUsername("username");
        user.setPassword("password");
        user.setNickname("nickname");
        user.setDepId(0);

        final MockHttpServletRequest request = new MockHttpServletRequest();
        final AjaxResult expectedResult = new AjaxResult();
        when(mockTsysUserDao.queryUserName("username")).thenReturn(null);

        // Run the test
        final AjaxResult result = adminControllerUnderTest.login(user, "captcha", null, false, request);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testLoginOut() {
        // Setup
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final AjaxResult expectedResult = new AjaxResult();

        // Run the test
        final AjaxResult result = adminControllerUnderTest.LoginOut(request, response);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testOut404() {
        // Setup
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final MockHttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        final String result = adminControllerUnderTest.Out404(request, response);

        // Verify the results
        assertEquals("redirect:/error/404", result);
    }

    @Test
    public void testOut403() {
        // Setup
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final MockHttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        final String result = adminControllerUnderTest.Out403(request, response);

        // Verify the results
        assertEquals("redirect:/error/403", result);
    }

    @Test
    public void testOut500() {
        // Setup
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final MockHttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        final String result = adminControllerUnderTest.Out500(request, response);

        // Verify the results
        assertEquals("redirect:/error/500", result);
    }

    @Test
    public void testOutqx() {
        // Setup
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final MockHttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        final String result = adminControllerUnderTest.Outqx(request, response);

        // Verify the results
        assertEquals("redirect:/error/500", result);
    }
}
