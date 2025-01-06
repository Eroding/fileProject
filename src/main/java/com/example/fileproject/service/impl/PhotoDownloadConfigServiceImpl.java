package com.example.fileproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fileproject.domain.PhotoDownloadConfig;
import com.example.fileproject.mapper.PhotoDownloadConfigMapper;
import com.example.fileproject.service.PhotoDownloadConfigService;

import org.springframework.stereotype.Service;

/**
* @author edison
* @description 针对表【photo_download_config(照片下载次数配置表)】的数据库操作Service实现
* @createDate 2025-01-04 15:04:49
*/
@Service
public class PhotoDownloadConfigServiceImpl extends ServiceImpl<PhotoDownloadConfigMapper, PhotoDownloadConfig>
    implements PhotoDownloadConfigService {

}




