package com.sg.simplyrugby.service;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.simplyrugby.mapper.GameDetailsMapper;
import com.sg.simplyrugby.model.simply.GameDetails;
import org.springframework.stereotype.Service;

@Service
public class GameDetailsService extends ServiceImpl<GameDetailsMapper, GameDetails> {
}
