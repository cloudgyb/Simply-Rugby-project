package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.sg.simplyrugby.model.simply.TrainingSession;
import com.sg.simplyrugby.model.simply.TrainingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainingSession")
public class TrainingSessionController {

    @Autowired
    private TrainingSessionService trainingSessionService;

    @PostMapping
    public boolean save(@RequestBody TrainingSession trainingSession) {
        return trainingSessionService.saveOrUpdate(trainingSession);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return trainingSessionService.removeById(id);
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
    public Page<TrainingSession> findPage(@RequestParam Integer pageNum,
                                          @RequestParam Integer pageSize,
                                          @RequestParam(defaultValue = "") String name) {
        QueryWrapper<TrainingSession> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return trainingSessionService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }
}
