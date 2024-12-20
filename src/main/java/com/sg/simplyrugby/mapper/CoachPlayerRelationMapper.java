package com.sg.simplyrugby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.simplyrugby.model.simply.CoachPlayerRelation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoachPlayerRelationMapper extends BaseMapper<CoachPlayerRelation> {
    // 这里可以添加自定义的数据库操作方法
}

