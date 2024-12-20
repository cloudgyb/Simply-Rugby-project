package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.simplyrugby.model.simply.Coach;
import com.sg.simplyrugby.model.simply.PlayerInfo;
import com.sg.simplyrugby.service.PlayerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/players")
public class PlayerInfoController {


    @Autowired
    private PlayerInfoService playerInfoService;

    @GetMapping("/{id}")
    public PlayerInfo getPlayerInfo(@PathVariable String id) {
        return playerInfoService.getById(id);
    }

    @PostMapping("/")
    public boolean createPlayerInfo(@RequestBody PlayerInfo playerInfo) {
        playerInfo.setId(UUID.randomUUID().toString());
        return playerInfoService.save(playerInfo);
    }

    @PutMapping("/{id}")
    public boolean updatePlayerInfo(@PathVariable String id, @RequestBody PlayerInfo playerInfo) {
        playerInfo.setId(id);
        return playerInfoService.updateById(playerInfo);
    }

    @DeleteMapping("/{id}")
    public boolean deletePlayerInfo(@PathVariable String id) {
        return playerInfoService.removeById(id);
    }

    @GetMapping("/page")
    public Page<PlayerInfo> page(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return playerInfoService.page(new Page<>(pageNum, pageSize));
    }

}
