package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.dto.PlayerInfoDTO;
import com.sg.simplyrugby.model.simply.PlayerInfo;
import com.sg.simplyrugby.service.PlayerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sg.simplyrugby.controller.BaseController.pageTable;

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
    public AjaxResult createPlayerInfo(@RequestBody PlayerInfoDTO playerInfo) {
        boolean b = playerInfoService.savePlayer(playerInfo);
        return b ? AjaxResult.success() : AjaxResult.error();
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
    public ResultTable page(PageDTO pageDTO) {
        String searchText = pageDTO.getSearchText();
        LambdaQueryWrapper<PlayerInfo> like = null;
        if (searchText != null && !searchText.isEmpty()) {
            like = new LambdaQueryWrapper<PlayerInfo>().like(PlayerInfo::getName, searchText).orderByAsc(PlayerInfo::getId);
        }
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getLimit());
        List<PlayerInfo> list = playerInfoService.list(
                like
        );
        PageInfo<PlayerInfo> pageInfo = new PageInfo<>(list);
        return pageTable(list, pageInfo.getTotal());
    }

}
