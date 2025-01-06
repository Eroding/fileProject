package com.example.fileproject.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fileproject.domain.Photo;
import com.example.fileproject.dto.PhotoDTO;
import com.example.fileproject.service.PhotoService;
import com.example.fileproject.mapper.PhotoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
* @author edison
* @description 针对表【photo(照片)】的数据库操作Service实现
* @createDate 2024-12-14 16:41:20
*/
@Service
@Slf4j
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo>
    implements PhotoService {


    @Resource
    private PhotoMapper photoMapper;

    @Override
    public String putFile(MultipartFile image) throws IOException {

        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构建新的文件名
        //文件扩展名
        String extname = originalFilename.substring(originalFilename.lastIndexOf("."));
        //随机名+文件扩展名
        String newFileName = UUID.randomUUID().toString()+extname;

        //将文件存储在服务器的磁盘目录
        String s = "/users/edison/code/" + newFileName;
        image.transferTo( new File(s));
        return s;

    }

    @Override
    public PageInfo<Photo> pageByCondition(PhotoDTO photo) {
        Page<Photo> pageQuery = new Page<>(photo.getPage(), photo.getRows());
        pageQuery.setOptimizeCountSql(false);
        PageHelper.startPage(photo.getPage(), photo.getRows());
        log.info("photo当前page:{},当前rows:{}", photo.getPage(), photo.getRows());
        List<Photo> photos = photoMapper.selectPhotoPage(pageQuery, photo);
        return new PageInfo<>(photos);

    }
}




