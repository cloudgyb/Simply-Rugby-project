package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.model.simply.League;
import com.sg.simplyrugby.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.sg.simplyrugby.controller.BaseController.pageTable;


/**
 * 联赛
 */
@Controller
@RequestMapping("/leagues")
public class LeagueController {
    @Autowired
    private LeagueService leagueService;

    @ResponseBody
    @GetMapping("/{id}")
    public League getLeague(@PathVariable String id) {
        return leagueService.getById(id);
    }

    @ResponseBody

    @PostMapping("/")
    public AjaxResult createLeague(@RequestBody League league) {
        league.setId(UUID.randomUUID().toString());
        boolean save = leagueService.save(league);
        return save ? AjaxResult.success() : AjaxResult.error();
    }

    @ResponseBody
    @PutMapping("/{id}")
    public AjaxResult updateLeague(@PathVariable String id, @RequestBody League league) {
        league.setId(id);
        return leagueService.updateById(league) ?AjaxResult.success() : AjaxResult.error();
    }

    // 分页查询示例
    @ResponseBody
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

    @ResponseBody
    @DeleteMapping("/{id}")
    public AjaxResult deleteLeague(@PathVariable String id) {
        boolean contains = id.contains(",");
        if (contains) {
            String[] split = id.split(",");
            List<String> collect = Arrays.stream(split).collect(Collectors.toList());
            boolean b = leagueService.removeBatchByIds(collect);
            return b ? AjaxResult.success() : AjaxResult.error();
        }
        return leagueService.removeById(id)?AjaxResult.success() : AjaxResult.error();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        League byId = leagueService.getById(id);
        mmap.put("league", byId);
        return "league/edit";
    }

}

