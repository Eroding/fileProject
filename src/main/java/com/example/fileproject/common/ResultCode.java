package com.example.fileproject.common;

import lombok.Getter;

/**
 * @author cnh
 * @date 2024/12/16 10:31 AM
 */

/**
 * 结果码枚举类
 */
@Getter
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200,"成功"),
    /**
     * 失败
     */
    FAIL(500,"失败");


    /**
     * code码
     */
    private final Integer code;

    /**
     * content
     */
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
