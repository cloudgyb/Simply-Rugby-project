package com.sg.simplyrugby.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;

import com.github.pagehelper.PageInfo;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.model.SysRole;
import com.sg.simplyrugby.model.SysUser;
import com.sg.simplyrugby.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户Controller
 * @ClassName: UserController
 * @author fuce
 * @date 2019-11-20 22:35
 */
@Api(value = "用户数据")
@Controller
@RequestMapping("/UserController")
public class UserController extends BaseController{
	
	private final String prefix = "admin/user";


	
	/**
	 * 展示跳转页面
	 * @param model
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@SaCheckPermission("system:user:view")
    public String view(ModelMap model)
    {
		return prefix + "/list";
    }
	
	
	/**
	 * list集合
	 */
	//@Log(title = "分页查询", action = "1")
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/list")
	@SaCheckPermission("system:user:list")
	@ResponseBody
	public ResultTable list(PageDTO pageDTO){
		PageInfo<SysUser> page=sysUserService.list(pageDTO) ;
		return pageTable(page.getList(),page.getTotal());
	}
	/**
	 * 新增跳转
	 * @param modelMap
	 * @return
	 * @author fuce
	 * @Date 2019年11月11日 下午4:14:51
	 */
	@ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
    	//添加角色列表
		List<SysRole> tsysRoleList=sysRoleService.queryList();

		//角色
		modelMap.put("tsysRoleList",tsysRoleList);
        return prefix + "/add";
    }
	/**
	 * 新增保存
	 * @param user
	 * @return
	 * @author fuce
	 * @Date 2019年11月11日 下午4:14:57
	 */
    @ApiOperation(value = "新增", notes = "新增")
	@PostMapping("/add")
	@SaCheckPermission("system:user:add")
	@ResponseBody
	public AjaxResult add(SysUser user, @RequestParam(value="roleIds", required = false)String roleIds){
		int b=sysUserService.insertUserRoles(user,roleIds);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
    //@Log(title = "删除用户", action = "1")
  	@ApiOperation(value = "删除", notes = "删除")
	@DeleteMapping("/remove")
	@SaCheckPermission("system:user:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=sysUserService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	

	
	/**
	 * 修改用户跳转
	 * @param id
	 * @param mmap
	 * @return
	 */
	@ApiOperation(value = "修改跳转", notes = "修改跳转")
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
		//查询所有角色
		List<RoleVo> roleVos=sysUserService.getUserIsRole(id);
		//岗位列表
		mmap.put("roleVos",roleVos);
        mmap.put("TsysUser", sysUserService.selectByPrimaryKey(id));
        return prefix + "/edit";
    }
	
	/**
     * 修改保存用户
     */
	//@Log(title = "修改保存用户", action = "1")
    @ApiOperation(value = "修改保存用户", notes = "修改保存用户")
    @SaCheckPermission("system:user:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysUser tsysUser,@RequestParam(value="roleIds", required = false)String roleIds)
    {
        return toAjax(sysUserService.updateUserRoles(tsysUser,roleIds));
    }

    
    
    /**
	 * 修改用户密码跳转
	 * @param id
	 * @param mmap
	 * @return
	 */
    //@Log(title = "修改用户密码", action = "1")
    @ApiOperation(value = "修改用户密码跳转", notes = "修改用户密码跳转")
	@GetMapping("/editPwd/{id}")
    public String editPwd(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("TsysUser", sysUserService.selectByPrimaryKey(id));
        return prefix + "/editPwd";
    }
	/**
     * 修改保存用户
     */
    //@Log(title = "修改用户密码", action = "1")
    @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
    @SaCheckPermission("system:user:editPwd")
    @PostMapping("/editPwd")
    @ResponseBody
    public AjaxResult editPwdSave(SysUser tsysUser)
    {
        return toAjax(sysUserService.updateUserPassword(tsysUser));
    }


}
