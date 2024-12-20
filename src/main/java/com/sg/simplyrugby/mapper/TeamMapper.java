package com.sg.simplyrugby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.simplyrugby.model.simply.Team;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamMapper extends BaseMapper<Team> {
    // MyBatis-Plus 已经提供了基本的 CRUD 操作，无需额外编写
}

