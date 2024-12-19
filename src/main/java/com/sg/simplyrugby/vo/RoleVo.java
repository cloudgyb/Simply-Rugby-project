package com.sg.simplyrugby.vo;


import com.sg.simplyrugby.model.SysRole;
import lombok.Data;

/**
 * 角色自定义数据
 * @author fuce 
 * @date: 2018年9月8日 上午12:18:59
 */
@Data
public class RoleVo extends SysRole {
	private static final long serialVersionUID = 1L;
	private boolean ischeck;//判断是否又这个权限

	public RoleVo(String roleId,String roleName,boolean ischeck) {
		super.setId(roleId);
		super.setName(roleName);
		this.ischeck = ischeck;
	}
}
