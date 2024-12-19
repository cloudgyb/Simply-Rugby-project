package com.sg.simplyrugby.satoken;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.SaSessionCustomUtil;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sg.simplyrugby.mapper.SysPermissionRoleMapper;
import com.sg.simplyrugby.mapper.SysRoleMapper;
import com.sg.simplyrugby.model.SysPermissionRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展 
 */
@Component 
public class StpInterfaceImpl implements StpInterface {



	private SysPermissionRoleMapper sysPermissionRoleMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper ;//角色dao
	
    /**
     * 返回一个账号所拥有的权限码集合 
     * <p> 注：权限变动时需要清除缓存：SaSessionCustomUtil.getSessionById("role-" + roleId).delete("Permission_List");
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
    	List<String> permList = new ArrayList<>();
    	for (String roleId : getRoleList(loginId, loginType)) {
    		SaSession roleSession = SaSessionCustomUtil.getSessionById("role-" + roleId);

			List<String> sysPermissionRoles = sysPermissionRoleMapper.queryPermsList(roleId);

    		List<String> list = roleSession.get("Permission_List", () -> sysPermissionRoles);
    		permList.addAll(list);
		}
    	return permList;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     * <p> 注：角色变动时需要清除缓存：StpUtil.getSessionByLoginId(userId).delete("Role_List");
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
    	SaSession session = StpUtil.getSessionByLoginId(loginId);
    	return session.get("Role_List", () -> sysRoleMapper.queryUserRoleId(String.valueOf(loginId)));
    }

}