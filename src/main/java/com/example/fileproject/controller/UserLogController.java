package com.example.fileproject.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fileproject.common.ResponseResult;
import com.example.fileproject.domain.Photo;
import com.example.fileproject.domain.PhotoUserDownloadConfig;
import com.example.fileproject.domain.User;
import com.example.fileproject.domain.UserLog;
import com.example.fileproject.dto.UserDTO;
import com.example.fileproject.dto.UserLogDTO;
import com.example.fileproject.dto.UserLogVO;
import com.example.fileproject.service.PhotoService;
import com.example.fileproject.service.PhotoUserDownloadConfigService;
import com.example.fileproject.service.UserLogService;
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
@RequestMapping("/userLog")
public class UserLogController {

    @Resource
    private UserLogService userLogService;




    @PostMapping("/create")
    public ResponseResult create(@RequestBody UserLog userLog)  {
        Boolean aBoolean = userLogService.create(userLog);
        return ResponseResult.success(aBoolean);
    }


    @GetMapping("/detail")
    public ResponseResult detail(@RequestParam Integer id) {
        return ResponseResult.success(userLogService.getById(id));
    }

    @PostMapping("/page")
    public ResponseResult<PageInfo<UserLogVO>> list(@RequestBody UserLogDTO userLog) {
        return ResponseResult.success(userLogService.findUserLogByPage(userLog));
    }
}
