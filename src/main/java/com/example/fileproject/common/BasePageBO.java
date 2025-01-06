package com.example.fileproject.common;

import lombok.Data;



/**
 * @author cnh
 * @since  2021/04/13
 */
@Data
public class BasePageBO {


    private Integer page = 1;


    private Integer rows = 10;
}
