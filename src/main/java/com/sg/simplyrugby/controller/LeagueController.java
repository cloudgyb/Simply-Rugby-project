package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.model.simply.GameDetails;
import com.sg.simplyrugby.model.simply.League;
import com.sg.simplyrugby.model.simply.PlayerInfo;
import com.sg.simplyrugby.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.sg.simplyrugby.controller.BaseController.pageTable;


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
    public AjaxResult createLeague(@RequestBody League league) {
        league.setId(UUID.randomUUID().toString());
        boolean save = leagueService.save(league);
        return save ? AjaxResult.success() : AjaxResult.error();
    }

    @PutMapping("/{id}")
    public AjaxResult updateLeague(@PathVariable String id, @RequestBody League league) {
        league.setId(id);
        return leagueService.updateById(league) ?AjaxResult.success() : AjaxResult.error();
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteLeague(@PathVariable String id) {
        return leagueService.removeById(id)?AjaxResult.success() : AjaxResult.error();
    }

    // 分页查询示例
    @GetMapping("/page")
    public ResultTable page(PageDTO pageDTO) {
        String searchText = pageDTO.getSearchText();
        LambdaQueryWrapper<League> like = null;
        if (searchText != null && !searchText.isEmpty()) {
            like = new LambdaQueryWrapper<League>().like(League::getName, searchText).orderByAsc(League::getId);
        }
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getLimit());
        List<League> list = leagueService.list(
                like
        );
        PageInfo<League> pageInfo = new PageInfo<>(list);
        return pageTable(list, pageInfo.getTotal());
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        League byId = leagueService.getById(id);
        mmap.put("league", byId);
        return "league/edit";
    }

}

