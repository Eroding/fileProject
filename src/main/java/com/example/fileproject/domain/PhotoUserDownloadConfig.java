package com.example.fileproject.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName photo_user_download_config
 */
@TableName(value ="photo_user_download_config")
@Data
public class PhotoUserDownloadConfig implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 剩余次数
     */
    private Integer useCount;

    /**
     * 使用日期
     */
    private Date useDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}