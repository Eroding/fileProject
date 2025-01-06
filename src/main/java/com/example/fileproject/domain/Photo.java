package com.example.fileproject.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 照片
 * @TableName photo
 */
@TableName(value ="photo")
@Data
public class Photo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 地址
     */
    private String url;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 标签
     */
    private Integer labelId;

    /**
     * 排序
     */
    private Integer serialNumber;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}