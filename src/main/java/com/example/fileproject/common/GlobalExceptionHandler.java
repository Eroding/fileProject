package com.example.fileproject.common;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author cnh
 * @date 2024/12/16 10:43 AM
 */


@RestControllerAdvice
@Order(99)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        e.printStackTrace();
        return ResponseResult.failed(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
    }


}
