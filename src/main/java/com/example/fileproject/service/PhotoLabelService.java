package com.example.fileproject.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fileproject.domain.PhotoLabel;
import com.example.fileproject.dto.PhotoLabelDTO;
import com.example.fileproject.dto.PhotoLabelVO;
import com.github.pagehelper.PageInfo;

/**
* @author edison
* @description 针对表【photo_label(照片标签关系表)】的数据库操作Service
* @createDate 2024-12-31 18:22:38
*/
public interface PhotoLabelService extends IService<PhotoLabel> {

    PageInfo<PhotoLabelVO> pageByCondition(PhotoLabelDTO photo);
}
