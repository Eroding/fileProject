package com.example.fileproject.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 照片下载次数配置表
 * @TableName photo_download_config
 */
@TableName(value ="photo_download_config")
@Data
public class PhotoDownloadConfig implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 次数
     */
    private Integer allCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}