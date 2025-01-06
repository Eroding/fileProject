package com.example.fileproject.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fileproject.domain.Photo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.fileproject.dto.PhotoDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
* @author edison
* @description 针对表【photo(照片)】的数据库操作Mapper
* @createDate 2024-12-14 16:41:20
* @Entity generator.domain.Photo
*/


public interface PhotoMapper extends BaseMapper<Photo> {

    List<Photo> selectPhotoPage(@Param("page")Page<Photo> pageQuery, @Param("req")PhotoDTO photo);
}




