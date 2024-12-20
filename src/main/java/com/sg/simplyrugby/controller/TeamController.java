package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.simplyrugby.model.simply.Coach;
import com.sg.simplyrugby.model.simply.Team;
import com.sg.simplyrugby.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public boolean createTeam(@RequestBody Team team) {
        team.setId(UUID.randomUUID().toString());
        return teamService.save(team);
    }

    @PutMapping("/{id}")
    public boolean updateTeam(@PathVariable String id, @RequestBody Team team) {
        team.setId(id);
        return teamService.updateById(team);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTeam(@PathVariable String id) {
        return teamService.removeById(id);
    }

    @GetMapping("/page")
    public Page<Team> page(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return teamService.page(new Page<>(pageNum, pageSize));
    }

}

