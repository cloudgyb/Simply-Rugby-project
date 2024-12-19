package com.sg.simplyrugby.service;

import cn.dev33.satoken.stp.StpUtil;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.mapper.SysRoleMapper;
import com.sg.simplyrugby.mapper.SysRoleUserMapper;
import com.sg.simplyrugby.mapper.SysUserMapper;
import com.sg.simplyrugby.model.SysRole;
import com.sg.simplyrugby.model.SysRoleUser;
import com.sg.simplyrugby.model.SysUser;
import com.sg.simplyrugby.util.ConvertUtil;
import com.sg.simplyrugby.util.MD5Util;
import com.sg.simplyrugby.util.SnowflakeIdWorker;
import com.sg.simplyrugby.util.StringUtils;
import com.sg.simplyrugby.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统用户
 * @ClassName: SysUserService
 * @author fuce
 * @date 2018年8月26日
 *
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {
	//生成的用户dao
	@Autowired
	private SysUserMapper tsysUserMapper;
	
	//生成的角色用户dao
	@Autowired
	private SysRoleUserMapper tSysRoleUserMapper;
	

	@Autowired
	private SysRoleMapper tsysRoleMapper;
	
	//自定义用户dao

	
	/**
	 * 分页查询
	 * @return
	 */
	 public PageInfo<SysUser> list(PageDTO pageDTO){
		 PageHelper.startPage(pageDTO.getPage(), pageDTO.getLimit());
		 List<SysUser> list= tsysUserMapper.selectList(new LambdaQueryWrapper<SysUser>().like(SysUser::getUsername,pageDTO.getSearchText()));
		 PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
		 return  pageInfo;
	 }

	
	@Transactional
	public int deleteByPrimaryKey(String ids) {
		List<String> lista= ConvertUtil.toListStrArray(ids);

		List<SysUser> sysUsers = tsysUserMapper.selectList(new LambdaQueryWrapper<SysUser>().in(SysUser::getId, lista));
		if(sysUsers !=null && sysUsers.size()>0){
			tSysRoleUserMapper.delete(new LambdaQueryWrapper<SysRoleUser>().in(SysRoleUser::getSysUserId,lista));
		}else {
			return 0;
		}
		return sysUsers.size();

	}
	
	/**
	 * 添加用户
	 */
	public int insertSelective(SysUser record) {
		return tsysUserMapper.insert(record);
	}
	
	/**
	 * 添加用户跟角色信息
	 * @param record
	 * @param roles
	 * @return
	 */
	@Transactional
	public int insertUserRoles(SysUser record,String roles) {
		String userid= SnowflakeIdWorker.getUUID();
		record.setId(userid);
		if(StringUtils.isNotEmpty(roles)){
			List<String> list_roles=ConvertUtil.toListStrArray(roles);
			 for (String rolesid : list_roles) {
				 SysRoleUser roleUser=new SysRoleUser(SnowflakeIdWorker.getUUID(), userid,rolesid);
				 tSysRoleUserMapper.insert(roleUser);
			}
		}
		
		//密码加密
		record.setPassword(MD5Util.encode(record.getPassword()));
		return tsysUserMapper.insert(record);
	}
	
	public SysUser selectByPrimaryKey(String id) {
		
		return tsysUserMapper.selectById(id);
	}

	

	
	/**
	 * 获取所有权限 并且增加是否有权限字段
	 * @return
	 */
	public List<RoleVo> getUserIsRole(String userid){
		List<RoleVo> list=new ArrayList<RoleVo>();
		//查询出我的权限
		List<SysRoleUser> myRoles= tSysRoleUserMapper.selectList(new LambdaQueryWrapper<SysRoleUser>().eq(SysRoleUser::getSysUserId,userid));
		List<String> roleIds =new ArrayList<>();

		if(CollectionUtil.isNotEmpty(myRoles)){
			for (SysRoleUser myRole : myRoles) {
				roleIds.add(myRole.getSysRoleId());
			}

		}
		//查询系统所有的角色
		List<SysRole> tsysRoles= tsysRoleMapper.selectList(new LambdaQueryWrapper<SysRole>().in(SysRole::getId,roleIds));

		if(StringUtils.isNotEmpty(tsysRoles)){
			for (SysRole tsysRole : tsysRoles) {
				Boolean isflag=false;
				RoleVo roleVo=new RoleVo(tsysRole.getId(),tsysRole.getName(), isflag);
				for (String roleId : roleIds) {
					if(tsysRole.getId().equals(roleId)){
						isflag=true;
						break;
					}
				}
				if(isflag){
					roleVo.setIscheck(true);
					list.add(roleVo);
				}else{
					list.add(roleVo);
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 修改用户密码
	 * @param record
	 * @return
	 */
	public int updateUserPassword(SysUser record) {
		record.setPassword(MD5Util.encode(record.getPassword()));
		//修改用户信息
		return tsysUserMapper.updateById(record);
	}
	
	
	/**
	 * 修改用户信息以及角色信息
	 * @param record
	 * @param roles
	 * @return
	 */
	@Transactional
	public int updateUserRoles(SysUser record,String roleIds) {
		//先删除这个用户的所有角色
		tSysRoleUserMapper.delete(new LambdaQueryWrapper<SysRoleUser>().eq(SysRoleUser::getSysUserId,record.getId()));
		if(StringUtils.isNotEmpty(roleIds)) {
			List<String> list_roles=ConvertUtil.toListStrArray(roleIds);
			//添加新的角色信息
			for (String role : list_roles) {
				SysRoleUser tSysRoleUser= new SysRoleUser(SnowflakeIdWorker.getUUID(), record.getId(), role);
				tSysRoleUserMapper.insert(tSysRoleUser);
			}
		}
		// 清除此用户角色信息缓存 
		StpUtil.getSessionByLoginId(record.getId()).delete("Role_List");
		
		//修改用户信息
		return tsysUserMapper.updateById(record);
	}


	public SysUser queryUserName(String username){
		List<SysUser> sysUsers = tsysUserMapper.selectList(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
		return sysUsers.get(0);
	}
	
}
