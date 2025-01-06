package com.example.fileproject.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fileproject.domain.UserLog;
import com.example.fileproject.dto.UserLogDTO;
import com.example.fileproject.dto.UserLogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author edison
* @description 针对表【user_log(日志记录表)】的数据库操作Mapper
* @createDate 2024-12-31 18:22:49
* @Entity generator.domain.UserLog
*/
public interface UserLogMapper extends BaseMapper<UserLog> {


    List<UserLogVO> selectLogPage( @Param("page")Page<Object> page, @Param("req") UserLogDTO req);


}




