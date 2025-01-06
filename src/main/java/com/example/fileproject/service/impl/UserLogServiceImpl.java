package com.example.fileproject.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fileproject.domain.Photo;
import com.example.fileproject.domain.PhotoUserDownloadConfig;
import com.example.fileproject.domain.User;
import com.example.fileproject.domain.UserLog;
import com.example.fileproject.dto.UserLogDTO;
import com.example.fileproject.dto.UserLogVO;
import com.example.fileproject.service.*;
import com.example.fileproject.mapper.UserLogMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
* @author edison
* @description 针对表【user_log(日志记录表)】的数据库操作Service实现
* @createDate 2024-12-31 18:22:49
*/
@Service
public class UserLogServiceImpl extends ServiceImpl<UserLogMapper, UserLog>
    implements UserLogService{

    @Resource
    private UserLogMapper userLogMapper;



    @Resource
    private PhotoService photoService;

    @Resource
    private UserService userService;

    @Resource
    private PhotoUserDownloadConfigService photoUserDownloadConfigService;

    @Resource
    private PhotoDownloadConfigService photoDownloadConfigService;

    @Override
    public PageInfo<UserLogVO> findUserLogByPage(UserLogDTO userLog) {

        Page<Object> pageQuery = new Page<>(userLog.getPage(), userLog.getRows());
        pageQuery.setOptimizeCountSql(false);

        List<UserLogVO> userLogVOS = userLogMapper.selectLogPage(pageQuery, userLog);

        for (UserLogVO userLogVO : userLogVOS) {
            switch (userLogVO.getType()){
                case 1:
                    userLogVO.setTypeName(userLogVO.getUserName()+"登录时间为:"+DateUtil.formatDateTime(userLogVO.getCreateTime()));
                    break;
                case 2:
                    userLogVO.setTypeName(userLogVO.getUserName()+"下载的图片为:"+userLogVO.getTargetContent()+",时间为:"+DateUtil.formatDateTime(userLogVO.getCreateTime()));
                    break;
                case 3:
                    userLogVO.setTypeName(userLogVO.getUserName()+"点开的图片:"+userLogVO.getTargetContent()+",时间为:"+DateUtil.formatDateTime(userLogVO.getCreateTime()));
                    break;
                default:
                    userLogVO.setTypeName("未知操作");
            }
        }
        return new PageInfo<>(userLogVOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(UserLog userLog) {
        boolean  status=true;
        //扣减次数
        if(userLog.getType() == 2 ) {
            String today = DateUtil.today();
            LambdaQueryWrapper<PhotoUserDownloadConfig> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(PhotoUserDownloadConfig::getUserId, userLog.getUserId());
            queryWrapper.eq(PhotoUserDownloadConfig::getUseDate, today);
            PhotoUserDownloadConfig userDownloadConfig = photoUserDownloadConfigService.getOne(queryWrapper);

            //新增一条
            if (userDownloadConfig == null) {
                PhotoUserDownloadConfig newConfig = new PhotoUserDownloadConfig();
                newConfig.setUserId(userLog.getUserId());
                newConfig.setUseDate(DateUtil.parse(DateUtil.today(), "yyyy-MM-dd"));
                newConfig.setUseCount(1);
                status = photoUserDownloadConfigService.save(newConfig);
            }else {
                //编辑数据
                LambdaUpdateWrapper<PhotoUserDownloadConfig> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(PhotoUserDownloadConfig::getUserId, userLog.getUserId());
                updateWrapper.eq(PhotoUserDownloadConfig::getUseDate, today);
                updateWrapper.set(PhotoUserDownloadConfig::getUseCount, userDownloadConfig.getUseCount() + 1);
                status = photoUserDownloadConfigService.update(updateWrapper);
            }
        }

        if(userLog.getType() == 2 || userLog.getType() == 3){
            userLog.setTargetId(userLog.getTargetId());
            Photo photo = photoService.getById(userLog.getTargetId());
            User user = userService.getById(userLog.getUserId());
            if(photo!= null){
                userLog.setTargetContent(photo.getName());
            }
            if(user!= null){
                userLog.setUserName(user.getName());
            }
            this.save(userLog);
        }
        return status;
    }
}




