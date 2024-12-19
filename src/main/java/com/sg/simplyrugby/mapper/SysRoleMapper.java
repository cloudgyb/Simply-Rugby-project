package com.sg.simplyrugby.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.simplyrugby.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select(" select r.id from t_sys_role r\n" +
            "\t\tLEFT JOIN t_sys_role_user ru ON  r.id=ru.sys_role_id\n" +
            "\t\twhere ru.sys_user_id=#{userid}")
    List<String> queryUserRoleId(@Param("userid") String userid);
}