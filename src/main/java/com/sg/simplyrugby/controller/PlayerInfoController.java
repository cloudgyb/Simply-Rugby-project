package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.simplyrugby.dto.PlayerInfoDTO;
import com.sg.simplyrugby.model.simply.PlayerInfo;
import com.sg.simplyrugby.service.PlayerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 球员
 */
@RestController
@RequestMapping("/players")
public class PlayerInfoController {


    @Autowired
    private PlayerInfoService playerInfoService;

    @GetMapping("/{id}")
    public PlayerInfoDTO getPlayerInfo(@PathVariable String id) {
        return playerInfoService.getInfo(id);
    }

    @PostMapping("/")
    public boolean createPlayerInfo(@RequestBody PlayerInfoDTO playerInfo) {
        return playerInfoService.savePlayer(playerInfo);
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
