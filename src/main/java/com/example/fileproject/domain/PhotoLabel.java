package com.example.fileproject.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 照片标签关系表
 * @TableName photo_label
 */
@TableName(value ="photo_label")
@Data
public class PhotoLabel implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 图片id
     */
    private Integer photoId;

    /**
     * 标签id
     */
    private Integer labelId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}