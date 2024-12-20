package com.sg.simplyrugby.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.simplyrugby.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    @Select("select distinct p.*,(select count(1) from sys_permission where pid=p.id) childcount" +
            "        from sys_permission_role spr,sys_role_user sru,sys_permission p " +
            "        where spr.role_id = sru.sys_role_id AND spr.permission_id = p.id and visible=0 " +
            " AND sru.sys_user_id=#{userId} " +
            " GROUP BY p.id " +
            " ORDER BY order_num  is null  ASC,order_num  ASC")
    List<SysPermission> getByAdminUserId(String userId);
}