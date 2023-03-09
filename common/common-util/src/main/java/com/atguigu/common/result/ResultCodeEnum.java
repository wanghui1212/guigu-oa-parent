package com.atguigu.common.result;

import lombok.Getter;

/**
 * @version 1.0
 * @Author wanghui
 * @Description 统一返回结果状态信息类
 * @Create 2023-03-02 17:17
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    LOGIN_ERROR(204,"认证失败")
    ;

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}
