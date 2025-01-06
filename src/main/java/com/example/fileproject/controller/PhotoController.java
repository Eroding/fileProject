package com.example.fileproject.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.fileproject.common.ResponseResult;
import com.example.fileproject.domain.Photo;
import com.example.fileproject.domain.User;
import com.example.fileproject.dto.PhotoDTO;
import com.example.fileproject.service.PhotoService;
import com.example.fileproject.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author cnh
 * @date 2024/12/16 10:26 AM
 */
@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Resource
    private PhotoService photoService;



    @PostMapping("/create")
    public ResponseResult saveFile(@RequestBody Photo photo)  {
        photoService.save(photo);
        return ResponseResult.success();
    }



    @PostMapping("/createList")
    public ResponseResult createList(@RequestBody List<Photo> photoList)  {
        boolean saveBatch = photoService.saveBatch(photoList);
        return ResponseResult.success(saveBatch);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody Photo photo)  {
        photoService.updateById(photo);
        return ResponseResult.success();
    }

    @PostMapping("/page")
    public ResponseResult<PageInfo<Photo>> page(@RequestBody PhotoDTO photo)  {
        return ResponseResult.success(photoService.pageByCondition(photo));
    }

    @GetMapping("/detail")
    public ResponseResult detail(@RequestParam Integer id)   {
        return ResponseResult.success(photoService.getById(id));
    }

    @GetMapping("/delete")
    public ResponseResult delete(@RequestParam Integer id)   {
        photoService.removeById(id);
        return ResponseResult.success();
    }
}
