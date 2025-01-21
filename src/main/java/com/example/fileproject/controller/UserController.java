package com.example.fileproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fileproject.common.ResponseResult;
import com.example.fileproject.domain.Photo;
import com.example.fileproject.domain.User;
import com.example.fileproject.dto.UserDTO;
import com.example.fileproject.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author cnh
 * @date 2024/12/16 10:26 AM
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult detail(@RequestBody User user) {
       User userResult = userService.login(user);
        if (userResult!= null) {
            return ResponseResult.data(userResult);
        } else {
            return ResponseResult.failed(500, "登录失败");
        }
    }


    @PostMapping("/create")
    public ResponseResult create(@RequestBody User user) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, user.getName());
        User existUser = userService.getOne(queryWrapper);
        if (existUser!= null) {
            return ResponseResult.failed(500, "用户名已存在");
        }
        Boolean save = userService.save(user);
        if (save) {
            return ResponseResult.success("创建成功");
        } else {
            return ResponseResult.failed(500, "创建失败");
        }
    }


    @PostMapping("/update")
    public ResponseResult update(@RequestBody User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName, user.getName());
        queryWrapper.ne(User::getId, user.getId());
        User existUser = userService.getOne(queryWrapper);
        if (existUser!= null) {
            return ResponseResult.failed(500, "用户名已存在");
        }
        Boolean update = userService.updateById(user);
        if (update) {
            return ResponseResult.success("更新成功");
        } else {
            return ResponseResult.failed(500, "更新失败");
        }
    }

    @GetMapping("/delete")
    public ResponseResult delete(@RequestParam Integer id) {
        Boolean delete = userService.removeById(id);
        if (delete) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failed(500, "删除失败");
        }
    }

    @GetMapping("/detail")
    public ResponseResult detail(@RequestParam Integer id) {
        return ResponseResult.success(userService.getById(id));
    }

    @PostMapping("/page")
    public ResponseResult<PageInfo<User>> list(@RequestBody UserDTO user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(user.getName()!= null, User::getName, user.getName());
        Page<User> pageQuery = new Page<>(user.getPage(), user.getRows());
        pageQuery.setOptimizeCountSql(false);
        PageHelper.startPage(user.getPage(), user.getRows());
        Page<User> page = userService.page(pageQuery, queryWrapper);
        return ResponseResult.success(new PageInfo<>(page.getRecords()));
    }
}
