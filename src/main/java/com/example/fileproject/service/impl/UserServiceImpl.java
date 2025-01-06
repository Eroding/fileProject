package com.example.fileproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fileproject.domain.User;
import com.example.fileproject.domain.UserLog;
import com.example.fileproject.service.UserLogService;
import com.example.fileproject.service.UserService;
import com.example.fileproject.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author edison
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-12-14 16:41:25
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Resource
    private UserLogService userLogService ;

    @Override
    public Boolean login(User user) {

        if(user.getName() == null || user.getPassword() == null){
            throw new RuntimeException("用户名或密码不能为空");
        }
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getName, user.getName())
                .eq(User::getPassword, user.getPassword())
                .orderByDesc(User::getId)
                .last("limit 1");

        User one = this.getOne(wrapper);
        if(one != null){

            //加日志
            UserLog userLog = new UserLog();
            userLog.setUserId(one.getId());
            userLog.setUserName(user.getName());
            userLog.setType(1);
            userLogService.save(userLog);
            return true;
        }
        return false;
    }
}




