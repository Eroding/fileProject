package com.example.fileproject.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.fileproject.common.BasePageBO;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户表
 * @author edison
 * @TableName user
 */

@Data
public class UserDTO  extends BasePageBO implements Serializable {
    /**
     * 主键id
     */

    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;


    private static final long serialVersionUID = 1L;

}