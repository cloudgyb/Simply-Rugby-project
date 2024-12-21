package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.model.simply.Coach;
import com.sg.simplyrugby.model.simply.PlayerInfo;
import com.sg.simplyrugby.model.simply.Team;
import com.sg.simplyrugby.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.sg.simplyrugby.controller.BaseController.pageTable;

/**
 * 球队
 */
@RestController
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/{id}")
    public Team getTeam(@PathVariable String id) {
        return teamService.getById(id);
    }

    @PostMapping("/")
    public AjaxResult createTeam(@RequestBody Team team) {
        team.setId(UUID.randomUUID().toString());
        boolean save = teamService.save(team);
        return save ? AjaxResult.success() : AjaxResult.error();
    }

    @PutMapping("/{id}")
    public AjaxResult updateTeam(@PathVariable String id, @RequestBody Team team) {
        team.setId(id);
        return teamService.updateById(team)?AjaxResult.success() : AjaxResult.error();
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteTeam(@PathVariable String id) {
        return teamService.removeById(id)?AjaxResult.success() : AjaxResult.error();
    }

    @GetMapping("/page")
    public ResultTable page(PageDTO pageDTO) {
        String searchText = pageDTO.getSearchText();
        LambdaQueryWrapper<Team> like = null;
        if (searchText != null && !searchText.isEmpty()) {
            like = new LambdaQueryWrapper<Team>().like(Team::getName, searchText).orderByAsc(Team::getId);
        }
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getLimit());
        List<Team> list = teamService.list(
                like
        );
        PageInfo<Team> pageInfo = new PageInfo<>(list);
        return pageTable(list, pageInfo.getTotal());
    }

}

