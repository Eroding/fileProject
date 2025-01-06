package com.example.fileproject.controller;

import com.example.fileproject.common.ResponseResult;
import com.example.fileproject.domain.Photo;
import com.example.fileproject.domain.PhotoLabel;
import com.example.fileproject.domain.PhotoLabelBatch;
import com.example.fileproject.dto.PhotoDTO;
import com.example.fileproject.dto.PhotoLabelDTO;
import com.example.fileproject.dto.PhotoLabelVO;
import com.example.fileproject.service.PhotoLabelService;
import com.example.fileproject.service.PhotoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cnh
 * @date 2024/12/16 10:26 AM
 */
@RestController
@RequestMapping("/photoLabel")
public class PhotoLabelController {

    @Resource
    private PhotoLabelService photoLabelService;



    @PostMapping("/create")
    public ResponseResult saveFile(@RequestBody PhotoLabel photoLabel)  {
        photoLabelService.save(photoLabel);
        return ResponseResult.success();
    }

    @PostMapping("/createBatch")
    public ResponseResult createBatch(@RequestBody PhotoLabelBatch photoLabelBatch)  {

        List<PhotoLabel> photoLabels = new ArrayList<>();
        for (Integer photoId :photoLabelBatch.getPhotoIdList()) {
            PhotoLabel photoLabel = new PhotoLabel();
            photoLabel.setPhotoId(photoId);
            photoLabel.setLabelId(photoLabelBatch.getLabelId());
            photoLabels.add(photoLabel);
        }
        photoLabelService.saveBatch(photoLabels);
        return ResponseResult.success();
    }



    @PostMapping("/update")
    public ResponseResult update(@RequestBody PhotoLabel photoLabel)  {
        photoLabelService.updateById(photoLabel);
        return ResponseResult.success();
    }

    @PostMapping("/page")
    public ResponseResult<PageInfo<PhotoLabelVO>> page(@RequestBody PhotoLabelDTO photo)  {
        return ResponseResult.success(photoLabelService.pageByCondition(photo));
    }


    @GetMapping("/delete")
    public ResponseResult delete(@RequestParam Integer id)   {
        photoLabelService.removeById(id);
        return ResponseResult.success();
    }

    @PostMapping("/deleteBatch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids)   {
        photoLabelService.removeByIds(ids);
        return ResponseResult.success();
    }
}
