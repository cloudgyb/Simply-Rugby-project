package com.sg.simplyrugby.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.simplyrugby.model.SysPermissionRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysPermissionRoleMapper extends BaseMapper<SysPermissionRole> {
    @Select("select perms from t_sys_permission p\n" +
            "\tleft join t_sys_permission_role pr on p.id=pr.permission_id\n" +
            "\twhere pr.role_id=#{roleid}")
    List<String> queryPermsList(@Param("roleId") String roleId);

}