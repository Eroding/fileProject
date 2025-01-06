package com.example.fileproject.enums;

import lombok.Getter;

/**
 * @author cnh
 * @date 2024/12/31 6:27 PM
 */

@Getter
public enum TypeEnum {
    /**
     * 动作类型
     */
    IMPORT(1, "登录时间"),
    LEAVE(2, "下载图片"),
    OVERTIME(3, "点击图片");

    /**
     * code码
     */
    private final Integer code;

    /**
     * content
     */
    private final String content;

    TypeEnum(Integer code, String content) {
        this.code = code;
        this.content = content;
    }



    /**
     * 根据code获取去value
     * @param code
     * @return
     */
    public static String getValueByCode(Byte code){
        for(TypeEnum entryStatusEnum: TypeEnum.values()){
            if(code.equals(entryStatusEnum.getCode())){
                return entryStatusEnum.getContent();
            }
        }
        return  null;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
