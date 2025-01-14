package com.example.fileproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fileproject.common.ResponseResult;
import com.example.fileproject.domain.Cooperator;
import com.example.fileproject.domain.Label;
import com.example.fileproject.domain.Photo;
import com.example.fileproject.domain.User;
import com.example.fileproject.dto.CooperatorDTO;
import com.example.fileproject.dto.PhotoDTO;
import com.example.fileproject.service.CooperatorService;
import com.example.fileproject.service.PhotoService;
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
@RequestMapping("/cooperator")
public class CooperatorController {

    @Resource
    private CooperatorService cooperatorService;



    @PostMapping("/create")
    public ResponseResult saveFile(@RequestBody Cooperator cooperator)  {
        cooperatorService.save(cooperator);
        return ResponseResult.success();
    }


    @PostMapping("/update")
    public ResponseResult update(@RequestBody Cooperator cooperator)  {
        cooperatorService.updateById(cooperator);
        return ResponseResult.success();
    }

    @PostMapping("/page")
    public ResponseResult<PageInfo<Cooperator>> page(@RequestBody CooperatorDTO cooperator)  {

        LambdaQueryWrapper<Cooperator> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(cooperator.getName()!= null, Cooperator::getName, cooperator.getName());
        queryWrapper.like(cooperator.getPicture()!= null, Cooperator::getPicture, cooperator.getPicture());
        queryWrapper.orderByDesc(Cooperator::getCreateTime);
        Page<Cooperator> pageQuery = new Page<>(cooperator.getPage(), cooperator.getRows());
        pageQuery.setOptimizeCountSql(false);
        Page<Cooperator> page = cooperatorService.page(pageQuery, queryWrapper);
        return ResponseResult.success(new PageInfo<>(page.getRecords()));
    }

    @GetMapping("/detail")
    public ResponseResult detail(@RequestParam Integer id)   {
        return ResponseResult.success(cooperatorService.getById(id));
    }

    @GetMapping("/delete")
    public ResponseResult delete(@RequestParam Integer id)   {
        cooperatorService.removeById(id);
        return ResponseResult.success();
    }
}
