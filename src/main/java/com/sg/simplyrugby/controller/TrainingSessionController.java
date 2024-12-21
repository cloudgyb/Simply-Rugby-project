package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.model.simply.Coach;
import com.sg.simplyrugby.model.simply.TrainingSession;
import com.sg.simplyrugby.service.TrainingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.sg.simplyrugby.controller.BaseController.pageTable;

@RestController
@RequestMapping("/trainingSession")
public class TrainingSessionController {

    @Autowired
    private TrainingSessionService trainingSessionService;

    @PostMapping
    public AjaxResult save(@RequestBody TrainingSession trainingSession) {
        trainingSession.setId(UUID.randomUUID().toString());
        boolean saveOrUpdate = trainingSessionService.saveOrUpdate(trainingSession);
        return saveOrUpdate ? AjaxResult.success() : AjaxResult.error();
    }

    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable String id) {
        return trainingSessionService.removeById(id) ?AjaxResult.success() : AjaxResult.error();
    }

    @GetMapping
    public List<TrainingSession> findAll() {
        return trainingSessionService.list();
    }

    @GetMapping("/{id}")
    public TrainingSession findById(@PathVariable String id) {
        return trainingSessionService.getById(id);
    }

    @GetMapping("/page")
    public ResultTable page(PageDTO pageDTO) {
        String searchText = pageDTO.getSearchText();
        LambdaQueryWrapper<TrainingSession> like = null;
        if (searchText != null && !searchText.isEmpty()) {
            like = new LambdaQueryWrapper<TrainingSession>().like(TrainingSession::getName, searchText).orderByAsc(TrainingSession::getId);
        }
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getLimit());
        List<TrainingSession> list = trainingSessionService.list(
                like
        );
        PageInfo<TrainingSession> pageInfo = new PageInfo<>(list);
        return pageTable(list, pageInfo.getTotal());
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        //查询所有角色
        TrainingSession byId = trainingSessionService.getById(id);
        mmap.put("trainingSession", byId);
        return "trainingSession/edit";
    }
}
