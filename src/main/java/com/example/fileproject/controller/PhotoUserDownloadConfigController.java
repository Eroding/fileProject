package com.example.fileproject.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.fileproject.common.ResponseResult;
import com.example.fileproject.domain.PhotoDownloadConfig;
import com.example.fileproject.domain.PhotoUserDownloadConfig;
import com.example.fileproject.service.PhotoDownloadConfigService;
import com.example.fileproject.service.PhotoUserDownloadConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cnh
 * @date 2024/12/16 10:26 AM
 */
@RestController
@RequestMapping("/userDownloadConfig")
public class PhotoUserDownloadConfigController {

    @Resource
    private PhotoUserDownloadConfigService photoUserDownloadConfigService;

    @Resource
    private PhotoDownloadConfigService photoDownloadConfigService;


    @GetMapping("/getCount")
    public ResponseResult getCount(Integer userId) {

        String today = DateUtil.today();
        LambdaQueryWrapper<PhotoUserDownloadConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PhotoUserDownloadConfig::getUserId, userId);
        queryWrapper.eq(PhotoUserDownloadConfig::getUseDate, today);
        PhotoUserDownloadConfig userDownloadConfig = photoUserDownloadConfigService.getOne(queryWrapper);

        LambdaQueryWrapper<PhotoDownloadConfig> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.orderByDesc(PhotoDownloadConfig::getId);
        queryWrapper1.last("LIMIT 1");
        PhotoDownloadConfig config = photoDownloadConfigService.getOne(queryWrapper1);
        if (config == null) {
            return ResponseResult.failed(500, "没有配置信息");
        }
        if (userDownloadConfig == null) {
            return ResponseResult.success(config.getAllCount());
        } else {
            return ResponseResult.success(config.getAllCount() -userDownloadConfig.getUseCount());
        }
    }


}
