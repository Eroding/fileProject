package com.example.fileproject.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志记录表
 * @TableName user_log
 */
@TableName(value ="user_log")
@Data
public class UserLog implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户id
     */
    private String userName;

    /**
     * 动作类型
     */
    private Integer type;

    /**
     * 目标表主键id
     */
    private Integer targetId;

    /**
     * 目标表实际内容
     */
    private String targetContent;

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