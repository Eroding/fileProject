package com.example.fileproject.service;

import com.example.fileproject.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author edison
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-12-14 16:41:25
*/
public interface UserService extends IService<User> {

    Boolean login(User user);
}
