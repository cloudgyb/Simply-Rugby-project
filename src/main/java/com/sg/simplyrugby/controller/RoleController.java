package com.sg.simplyrugby.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;

import com.github.pagehelper.PageInfo;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.model.SysRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 角色Controller
 * @author fuce 
 * @date: 2018年9月2日 下午8:08:21
 */
@Api(value = "用户角色")
@Controller
@RequestMapping("/RoleController")
public class RoleController extends BaseController{
	
	
	//跳转页面参数
	private final String prefix = "admin/role";
	
	/**
	 * 展示页面
	 * @param model
	 * @return
	 * @author fuce
	 * @Date 2019年11月11日 下午4:01:58
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@SaCheckPermission("system:role:view")
    public String view(ModelMap model)
    {
		return prefix + "/list";
    }
	
	/**
	 * 角色列表
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/list")
	@SaCheckPermission("system:role:list")
	@ResponseBody
	public ResultTable list(PageDTO pageDTO){
		PageInfo<SysRole> page=sysRoleService.list(pageDTO) ;
		return pageTable(page.getList(),page.getTotal());
	}

	/**
     * 新增角色
     */
	@ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
	
	
    /**
     * 角色添加
     * @param role
     * @return
     */
	//@Log(title = "角色添加", action = "1")
	@ApiOperation(value = "新增", notes = "新增")
	@PostMapping("/add")
	@SaCheckPermission("system:role:add")
	@ResponseBody
	public AjaxResult add(@RequestBody SysRole role){
		int b=sysRoleService.insertSelective(role);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 删除角色
	 * @param ids
	 * @return
	 */
	//@Log(title = "删除角色", action = "1")
	@ApiOperation(value = "删除", notes = "删除")
	@DeleteMapping("/remove")
	@SaCheckPermission("system:role:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=sysRoleService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 检查角色
	 * @param SysRole
	 * @return
	 */
	@ApiOperation(value = "检查Name唯一", notes = "检查Name唯一")
	@PostMapping("/checkNameUnique")
	@ResponseBody
	public int checkNameUnique(SysRole SysRole){
		int b=sysRoleService.checkNameUnique(SysRole);
		if(b>0){
			return 1;
		}else{
			return 0;
		}
	}
	
	
	/**
	 * 修改角色
	 * @param id
	 * @param mmap
	 * @return
	 */
	@ApiOperation(value = "修改跳转", notes = "修改跳转")
	@GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") String id, ModelMap mmap)
    {
        mmap.put("sysRole", sysRoleService.selectByPrimaryKey(id));
        return prefix + "/edit";
    }



    /**
     * 修改保存角色
     */
	//@Log(title = "修改保存角色", action = "1")
	@ApiOperation(value = "修改保存", notes = "修改保存")
    @SaCheckPermission("system:role:edit")
    @PutMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody SysRole SysRole)
    {
    	int i=sysRoleService.updateByPrimaryKeySelective(SysRole);
        return toAjax(i);
    }




}
