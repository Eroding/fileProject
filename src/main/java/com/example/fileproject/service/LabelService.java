package com.example.fileproject.service;

import com.example.fileproject.domain.Label;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.fileproject.dto.LabelVO;

import java.util.List;

/**
* @author edison
* @description 针对表【label(标签)】的数据库操作Service
* @createDate 2024-12-14 16:40:56
*/
public interface LabelService extends IService<Label> {

    List<LabelVO> listForTree();
}
