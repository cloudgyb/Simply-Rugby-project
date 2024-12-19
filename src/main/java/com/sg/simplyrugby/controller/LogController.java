package com.sg.simplyrugby.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 日志记录controller
 * @author fuce 
 * @date: 2018年9月30日 下午9:28:31
 */
@Controller
@Api(value = "日志记录")
@RequestMapping("/LogController")
public class LogController extends BaseController{

	//跳转页面参数
	private final String prefix = "admin/log";
	
	/**
	 * 日志记录展示页面
	 * @param model
	 * @return
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@SaCheckPermission("system:log:view")
    public String view(ModelMap model)
    {
        return prefix + "/list";
    }
	



	

    
    
}
