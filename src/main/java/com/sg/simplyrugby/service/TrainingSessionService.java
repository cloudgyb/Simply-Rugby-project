package com.sg.simplyrugby.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.simplyrugby.mapper.TrainingSessionMapper;
import com.sg.simplyrugby.model.simply.TrainingSession;
import org.springframework.stereotype.Service;

@Service
public class TrainingSessionService extends ServiceImpl<TrainingSessionMapper, TrainingSession> {
}

