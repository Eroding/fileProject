package com.example.fileproject.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 标签
 * @TableName label
 */

@Data
public class LabelVO implements Serializable {
    /**
     * 
     */

    private Integer id;

    /**
     * 标签名字
     */
    private String labelName;

    /**
     * 父类id
     */
    private Integer parentId;

    /**
     * 等级
     */
    private Integer level;

    private Integer isDelete;


    private List<LabelVO> childrenLabel;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}