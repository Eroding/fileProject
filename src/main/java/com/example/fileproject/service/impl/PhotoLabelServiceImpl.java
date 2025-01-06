package com.example.fileproject.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.fileproject.domain.Photo;
import com.example.fileproject.domain.PhotoLabel;
import com.example.fileproject.dto.PhotoLabelDTO;
import com.example.fileproject.dto.PhotoLabelVO;
import com.example.fileproject.service.PhotoLabelService;
import com.example.fileproject.mapper.PhotoLabelMapper;
import com.example.fileproject.service.PhotoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author edison
 * @description 针对表【photo_label(照片标签关系表)】的数据库操作Service实现
 * @createDate 2024-12-31 18:22:38
 */
@Service
@Slf4j
public class PhotoLabelServiceImpl extends ServiceImpl<PhotoLabelMapper, PhotoLabel>
        implements PhotoLabelService {

    @Resource
    private PhotoLabelMapper photoLabelMapper;

    @Resource
    private PhotoService photoService;

    @Override
    public PageInfo<PhotoLabelVO> pageByCondition(PhotoLabelDTO photoLabelDTO) {
        Page<PhotoLabelVO> pageQuery = new Page<>(photoLabelDTO.getPage(), photoLabelDTO.getRows());
        pageQuery.setOptimizeCountSql(false);
        PageHelper.startPage(photoLabelDTO.getPage(), photoLabelDTO.getRows());
        log.info("photo当前page:{},当前rows:{}", photoLabelDTO.getPage(), photoLabelDTO.getRows());
        List<PhotoLabelVO> photos = photoLabelMapper.selectPhotoLabelPage(pageQuery, photoLabelDTO);
        for (PhotoLabelVO photoLabelVO : photos) {
            Photo photo = photoService.getById(photoLabelVO.getPhotoId());
            if (photo != null) {
                photoLabelVO.setName(photo.getName());
                photoLabelVO.setUrl(photo.getUrl());
            }
        }
        return new PageInfo<>(photos);
    }
}



