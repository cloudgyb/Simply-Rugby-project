package com.sg.simplyrugby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.simplyrugby.model.simply.PlayerInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlayerInfoMapper extends BaseMapper<PlayerInfo> {
}
