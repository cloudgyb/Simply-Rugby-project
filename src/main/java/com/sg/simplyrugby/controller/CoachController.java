package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.model.simply.Coach;
import com.sg.simplyrugby.service.CoachService;
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
 * 教练
 */
@Controller
@RequestMapping("/coaches")
public class CoachController {
    @Autowired
    private CoachService coachService;

    @ResponseBody
    @GetMapping("/{id}")
    public Coach getCoach(@PathVariable String id) {
        return coachService.getById(id);
    }

    @ResponseBody

    @PostMapping("/")
    public AjaxResult createCoach(@RequestBody Coach coach) {
        coach.setId(UUID.randomUUID().toString());
        boolean save = coachService.save(coach);
        return save ? AjaxResult.success() : AjaxResult.error();
    }

    @ResponseBody

    @PutMapping("/{id}")
    public AjaxResult updateCoach(@PathVariable String id, @RequestBody Coach coach) {
        coach.setId(id);
        boolean b = coachService.updateById(coach);
        return b ? AjaxResult.success() : AjaxResult.error();

    }

    @ResponseBody

    @DeleteMapping("/{id}")
    public AjaxResult deleteCoach(@PathVariable String id) {
        if (id.contains(",")) {
            return coachService.removeBatchByIds(
                    Arrays.stream(id.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList())) ? AjaxResult.success() : AjaxResult.error();
        }
        boolean b = coachService.removeById(id);
        return b ? AjaxResult.success() : AjaxResult.error();
    }

    @ResponseBody

    // 分页查询示例
    @GetMapping("/page")
    public ResultTable page(PageDTO pageDTO) {
        String searchText = pageDTO.getSearchText();
        LambdaQueryWrapper<Coach> like = null;
        if (searchText != null && !searchText.isEmpty()) {
            like = new LambdaQueryWrapper<Coach>().like(Coach::getFullName, searchText).orderByAsc(Coach::getId);
        }
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getLimit());
        List<Coach> list = coachService.list(
                like
        );
        PageInfo<Coach> pageInfo = new PageInfo<>(list);
        return pageTable(list, pageInfo.getTotal());
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        //查询所有角色
        Coach byId = coachService.getById(id);
        mmap.put("coach", byId);
        return "coach/edit";
    }
}
