package com.example.fileproject.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fileproject.domain.UserLog;
import com.example.fileproject.dto.UserLogDTO;
import com.example.fileproject.dto.UserLogVO;
import com.github.pagehelper.PageInfo;

/**
* @author edison
* @description 针对表【user_log(日志记录表)】的数据库操作Service
* @createDate 2024-12-31 18:22:49
*/
public interface UserLogService extends IService<UserLog> {


    PageInfo<UserLogVO> findUserLogByPage(UserLogDTO userLog);

    Boolean create(UserLog userLog);
}
