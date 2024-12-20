package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.simplyrugby.model.simply.League;
import com.sg.simplyrugby.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * 联赛
 */
@RestController
@RequestMapping("/leagues")
public class LeagueController {
    @Autowired
    private LeagueService leagueService;

    @GetMapping("/{id}")
    public League getLeague(@PathVariable String id) {
        return leagueService.getById(id);
    }

    @PostMapping("/")
    public boolean createLeague(@RequestBody League league) {
        league.setId(UUID.randomUUID().toString());
        return leagueService.save(league);
    }

    @PutMapping("/{id}")
    public boolean updateLeague(@PathVariable String id, @RequestBody League league) {
        league.setId(id);
        return leagueService.updateById(league);
    }

    @DeleteMapping("/{id}")
    public boolean deleteLeague(@PathVariable String id) {
        return leagueService.removeById(id);
    }

    // 分页查询示例
    @GetMapping("/page")
    public Page<League> page(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return leagueService.page(new Page<>(pageNum, pageSize));
    }
}

