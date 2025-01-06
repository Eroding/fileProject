package com.example.fileproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fileproject.common.ResponseResult;
import com.example.fileproject.domain.Label;
import com.example.fileproject.domain.User;
import com.example.fileproject.dto.LabelDTO;
import com.example.fileproject.dto.LabelVO;
import com.example.fileproject.service.LabelService;
import com.example.fileproject.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cnh
 * @date 2024/12/16 10:26 AM
 */
@RestController
@RequestMapping("/label")
public class LabelController {

    @Resource
    private LabelService labelService;

    @PostMapping("/create")
    public ResponseResult create(@RequestBody Label label) {
        labelService.save(label);
        return ResponseResult.success();
    }


    @PostMapping("/update")
    public ResponseResult update(@RequestBody Label label) {
        labelService.updateById(label);
        return ResponseResult.success();
    }

    @GetMapping("/detail")
    public ResponseResult<Label> detail(@RequestParam Integer id) {
        return ResponseResult.success(labelService.getById(id));
    }

    @GetMapping("/delete")
    public ResponseResult delete(@RequestParam Integer id) {
        LambdaUpdateWrapper<Label> wrapper = Wrappers.<Label>lambdaUpdate()
                .eq(Label::getId, id)
                .set(Label::getIsDelete, 1);
        return ResponseResult.success(labelService.update(wrapper));
    }

    @PostMapping("/page")
    public ResponseResult<PageInfo<Label>> list(@RequestBody LabelDTO label) {
        LambdaQueryWrapper<Label> wrapper = Wrappers.<Label>lambdaQuery()
                .eq(label.getLabelName() != null, Label::getLabelName, label.getLabelName())
                .eq(label.getLevel() != null, Label::getLevel, label.getLevel())
                .eq(Label::getIsDelete, 0);
        Page<Label> pageQuery = new Page<>(label.getPage(), label.getRows());
        pageQuery.setOptimizeCountSql(false);
        PageHelper.startPage(label.getPage(), label.getRows());
        Page<Label> page = labelService.page(pageQuery, wrapper);
        return ResponseResult.success(new PageInfo<>(page.getRecords()));
    }

    @GetMapping("/listForTree")
    public ResponseResult<List<LabelVO>> listForTree() {
        return ResponseResult.success(labelService.listForTree());
    }
}
