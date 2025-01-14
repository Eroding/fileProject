package com.example.fileproject.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.fileproject.common.ResponseResult;
import com.example.fileproject.domain.PhotoDownloadConfig;
import com.example.fileproject.domain.PhotoUserDownloadConfig;
import com.example.fileproject.domain.User;
import com.example.fileproject.service.PhotoDownloadConfigService;
import com.example.fileproject.service.PhotoUserDownloadConfigService;
import com.example.fileproject.service.UserService;
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

    @Resource
    private UserService userService;


    @GetMapping("/getCount")
    public ResponseResult getCount(Integer userId) {

        String today = DateUtil.today();
        LambdaQueryWrapper<PhotoUserDownloadConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PhotoUserDownloadConfig::getUserId, userId);
        queryWrapper.eq(PhotoUserDownloadConfig::getUseDate, today);
        PhotoUserDownloadConfig userDownloadConfig = photoUserDownloadConfigService.getOne(queryWrapper);

        User user = userService.getById(userId);
        if (user == null || user.getDownloadCount() == null ) {
            return ResponseResult.failed(500, "当前用户没有配置信息,请给当前用户配置默认的下载次数");
        }
        if (userDownloadConfig == null) {
            return ResponseResult.success(user.getDownloadCount());
        } else {
            if(user.getDownloadCount().equals(-1)){
                return ResponseResult.success(user.getDownloadCount());
            }else {
                return ResponseResult.success(user.getDownloadCount() - userDownloadConfig.getUseCount());
            }
        }
    }


}
