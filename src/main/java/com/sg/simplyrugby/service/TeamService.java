package com.sg.simplyrugby.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.simplyrugby.mapper.TeamMapper;
import com.sg.simplyrugby.model.simply.Team;
import org.springframework.stereotype.Service;


@Service
public class TeamService extends ServiceImpl<TeamMapper, Team> {

}