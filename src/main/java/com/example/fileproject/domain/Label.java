package com.example.fileproject.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 标签
 * @TableName label
 */
@TableName(value ="label")
@Data
public class Label implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
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


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}