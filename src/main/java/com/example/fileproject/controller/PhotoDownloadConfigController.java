package com.example.fileproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fileproject.common.ResponseResult;
import com.example.fileproject.domain.Photo;
import com.example.fileproject.domain.PhotoDownloadConfig;
import com.example.fileproject.domain.User;
import com.example.fileproject.domain.UserLog;
import com.example.fileproject.dto.UserLogDTO;
import com.example.fileproject.dto.UserLogVO;
import com.example.fileproject.service.PhotoDownloadConfigService;
import com.example.fileproject.service.PhotoService;
import com.example.fileproject.service.UserLogService;
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
@RequestMapping("/downloadConfig")
public class PhotoDownloadConfigController {

    @Resource
    private PhotoDownloadConfigService photoDownloadConfigService;


    @PostMapping("/save")
    public ResponseResult saveFile(@RequestBody PhotoDownloadConfig config)  {
        photoDownloadConfigService.save(config);
        return ResponseResult.success();
    }


    @PostMapping("/update")
    public ResponseResult detail(@RequestBody PhotoDownloadConfig config) {
        photoDownloadConfigService.updateById(config);
        return ResponseResult.success();
    }

    @GetMapping("/list")
    public ResponseResult<List<PhotoDownloadConfig>> list() {
        return ResponseResult.success(photoDownloadConfigService.list());
    }
}
