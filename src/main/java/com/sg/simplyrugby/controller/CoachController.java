package com.sg.simplyrugby.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.simplyrugby.model.simply.Coach;
import com.sg.simplyrugby.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * 教练
 */
@RestController
@RequestMapping("/coaches")
public class CoachController {
    @Autowired
    private CoachService coachService;

    @GetMapping("/{id}")
    public Coach getCoach(@PathVariable String id) {
        return coachService.getById(id);
    }

    @PostMapping("/")
    public boolean createCoach(@RequestBody Coach coach) {
        coach.setId(UUID.randomUUID().toString());
        return coachService.save(coach);
    }

    @PutMapping("/{id}")
    public boolean updateCoach(@PathVariable String id, @RequestBody Coach coach) {
        coach.setId(id);
        return coachService.updateById(coach);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCoach(@PathVariable String id) {
        return coachService.removeById(id);
    }

    // 分页查询示例
    @GetMapping("/page")
    public Page<Coach> page(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return coachService.page(new Page<>(pageNum, pageSize));
    }
}
