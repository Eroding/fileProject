package com.example.fileproject.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.fileproject.common.BasePageBO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 照片
 * @TableName photo
 */

@Data
public class PhotoDTO extends BasePageBO implements Serializable {
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
     * 一级标签id
     */
    private Integer labelId;


    private String vagueName;

    /**
     * 二级标签id
     */
    private Integer serialNumber;



    private List<Integer> labelIdList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}