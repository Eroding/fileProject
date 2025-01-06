package com.example.fileproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.fileproject.domain.Photo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fileproject.dto.PhotoDTO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author edison
* @description 针对表【photo(照片)】的数据库操作Service
* @createDate 2024-12-14 16:41:20
*/
public interface PhotoService extends IService<Photo> {

    String putFile(MultipartFile file) throws IOException;

    PageInfo<Photo> pageByCondition(PhotoDTO photo);
}
