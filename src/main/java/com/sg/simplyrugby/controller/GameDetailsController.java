package com.sg.simplyrugby.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sg.simplyrugby.common.AjaxResult;
import com.sg.simplyrugby.common.PageDTO;
import com.sg.simplyrugby.common.ResultTable;
import com.sg.simplyrugby.model.simply.GameDetails;
import com.sg.simplyrugby.service.GameDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.sg.simplyrugby.controller.BaseController.pageTable;

/**
 * GameDetails 控制器，用于处理与比赛详情相关的 HTTP 请求
 */
@Controller
@RequestMapping("/gameDetails")
public class GameDetailsController {

    @Autowired
    private GameDetailsService gameDetailsService;

    /**
     * 添加比赛详情
     *
     * @param gameDetails 比赛详情实体
     * @return 成功或错误信息
     */
    @ResponseBody
    @PostMapping
    public AjaxResult AjaxResult(@RequestBody GameDetails gameDetails) {
        boolean result = gameDetailsService.saveOrUpdate(gameDetails);
        return result ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 根据ID删除比赛详情
     *
     * @param id 比赛详情ID
     * @return 成功或错误信息
     */
    @ResponseBody
    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable String id) {
        boolean contains = id.contains(",");
        if (contains) {
            String[] split = id.split(",");
            List<String> collect = Arrays.stream(split).collect(Collectors.toList());
            boolean b = gameDetailsService.removeBatchByIds(collect);
            return b ? AjaxResult.success() : AjaxResult.error();
        }
        boolean result = gameDetailsService.removeById(id);
        return result ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 更新比赛详情
     *
     * @param gameDetails 比赛详情实体
     * @return 成功或错误信息
     */
    @ResponseBody
    @PutMapping("/{id}")
    public AjaxResult update(@PathVariable String id, @RequestBody GameDetails gameDetails) {
        gameDetails.setId(id);
        boolean result = gameDetailsService.updateById(gameDetails);
        return result ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 根据ID查询比赛详情
     *
     * @param id 比赛详情ID
     * @return 比赛详情实体
     */
    @ResponseBody
    @GetMapping("/{id}")
    public GameDetails getById(@PathVariable String id) {
        return gameDetailsService.getById(id);
    }

    /**
     * 查询所有比赛详情
     *
     * @return 比赛详情列表
     */
    @ResponseBody
    @GetMapping
    public List<GameDetails> list() {
        return gameDetailsService.list();
    }

    /**
     * 分页查询比赛详情
     *
     * @return 分页对象
     */
    @ResponseBody
    @GetMapping("/page")
    public ResultTable page(PageDTO pageDTO) {
        String searchText = pageDTO.getSearchText();
        LambdaQueryWrapper<GameDetails> like = null;
        if (searchText != null && !searchText.isEmpty()) {
            like = new LambdaQueryWrapper<GameDetails>().like(GameDetails::getGameName, searchText).orderByAsc(GameDetails::getId);
        }
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getLimit());
        List<GameDetails> list = gameDetailsService.list(
                like
        );
        PageInfo<GameDetails> pageInfo = new PageInfo<>(list);
        return pageTable(list, pageInfo.getTotal());
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        GameDetails byId = gameDetailsService.getById(id);
        mmap.put("gameDetails", byId);
        return "gameDetails/edit";
    }
}

