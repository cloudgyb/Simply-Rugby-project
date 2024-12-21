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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.sg.simplyrugby.controller.BaseController.pageTable;

/**
 * 球员
 */
@Controller
@RequestMapping("/players")
public class PlayerInfoController {
    @Autowired
    private PlayerInfoService playerInfoService;

    @ResponseBody
    @GetMapping("/{id}")
    public PlayerInfoDTO getPlayerInfo(@PathVariable String id) {
        return playerInfoService.getInfo(id);
    }

    @ResponseBody
    @PostMapping("/")
    public AjaxResult createPlayerInfo(@RequestBody PlayerInfoDTO playerInfo) {
        boolean b = playerInfoService.savePlayer(playerInfo);
        return b ? AjaxResult.success() : AjaxResult.error();
    }

    @ResponseBody
    @PutMapping("/{id}")
    public AjaxResult updatePlayerInfo(@PathVariable String id, @RequestBody PlayerInfo playerInfo) {
        playerInfo.setId(id);
        return playerInfoService.updateById(playerInfo) ? AjaxResult.success() : AjaxResult.error();
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public AjaxResult deletePlayerInfo(@PathVariable String id) {
        boolean contains = id.contains(",");
        if (contains) {
            String[] split = id.split(",");
            List<String> collect = Arrays.stream(split).collect(Collectors.toList());
            boolean b = playerInfoService.removeBatchByIds(collect);
            return b ? AjaxResult.success() : AjaxResult.error();
        }
        return playerInfoService.removeById(id) ? AjaxResult.success() : AjaxResult.error();
    }

    @ResponseBody
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

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        //查询所有角色
        PlayerInfoDTO dto = playerInfoService.getInfo(id);
        mmap.put("player", dto);
        return "player/edit";
    }

}
