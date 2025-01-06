package com.example.fileproject.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fileproject.domain.PhotoLabel;
import com.example.fileproject.dto.PhotoLabelDTO;
import com.example.fileproject.dto.PhotoLabelVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author edison
* @description 针对表【photo_label(照片标签关系表)】的数据库操作Mapper
* @createDate 2024-12-31 18:22:38
* @Entity generator.domain.PhotoLabel
*/
public interface PhotoLabelMapper extends BaseMapper<PhotoLabel> {

    List<PhotoLabelVO> selectPhotoLabelPage(@Param("page")Page<PhotoLabelVO> pageQuery,@Param("req") PhotoLabelDTO photo);
}




