package com.sg.simplyrugby.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.simplyrugby.dto.PlayerInfoDTO;
import com.sg.simplyrugby.mapper.PlayerInfoMapper;
import com.sg.simplyrugby.model.simply.Coach;
import com.sg.simplyrugby.model.simply.CoachPlayerRelation;
import com.sg.simplyrugby.model.simply.PlayerInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlayerInfoService extends ServiceImpl<PlayerInfoMapper, PlayerInfo>  {
    @Autowired
    private PlayerInfoMapper playerInfoMapper;
    @Autowired
    private CoachService coachService;

    @Autowired
    private  CoachPlayerRelationService coachPlayerRelationService;
    public boolean savePlayer(PlayerInfoDTO playerInfo) {
        String playId = UUID.randomUUID().toString();
        playerInfo.setId(UUID.randomUUID().toString());
        List<String> coachIds = playerInfo.getCoachIds();

        int insert = playerInfoMapper.insert(playerInfo);
        if (insert>0){
            if (CollectionUtil.isEmpty(coachIds)){
                return true;
            }else {
                List<CoachPlayerRelation> coachPlayerRelations = new ArrayList<>();
                CoachPlayerRelation coachPlayerRelatio;
                for (String coachId : coachIds) {
                    coachPlayerRelatio= new CoachPlayerRelation();
                    coachPlayerRelatio.setId(UUID.randomUUID().toString());
                    coachPlayerRelatio.setPlayerId(playId);
                    coachPlayerRelatio.setCoachId(coachId);
                    coachPlayerRelations.add(coachPlayerRelatio);
                }
                coachPlayerRelationService.saveBatch(coachPlayerRelations);
            }
        }
       return true;
    }

    public PlayerInfoDTO getInfo(String id) {
        PlayerInfoDTO dto = new PlayerInfoDTO();
        PlayerInfo byId = this.getById(id);

        List<CoachPlayerRelation> list = coachPlayerRelationService.list(new LambdaQueryWrapper<CoachPlayerRelation>().eq(CoachPlayerRelation::getPlayerId, id));
        List<String> ids = list.stream()
                .map(CoachPlayerRelation::getCoachId)
                .collect(Collectors.toList());

        BeanUtils.copyProperties(byId,dto);
        dto.setCoachIds(ids);

        List<Coach> list1 = coachService.list(new LambdaQueryWrapper<Coach>().eq(Coach::getId, ids));
        if (CollectionUtil.isNotEmpty(list1)){
            List<String> coachNames = list1.stream()
                    .map(Coach::getFullName)
                    .collect(Collectors.toList());
            dto.setCoachNames(coachNames);
        }


        return dto;
    }
}

