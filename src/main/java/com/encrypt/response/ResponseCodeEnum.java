package com.encrypt.response;
/**
 * ClassName: ResponseCodeEnum
 * Package: org.common.response
 * Description:
 *
 * @Author: @weixueshi
 * @Create: 2024/3/10 - 11:01
 * @Version: v1.0
 */

import lombok.Getter;

/**
 * 统一响应枚举值
 */
@Getter
public enum ResponseCodeEnum {
    FAIL(403,"failed"),
    SUCCESS(200,"success");

    /**
     * 响应码
     */
    private final int code;

    /**
     * 响应消息
     */
    private final String message;

    /**
     * 如何定义一个通用的枚举类
     * 举值-->构造-->遍历
     */

    /**
     * 构造
     * @param code
     * @param message
     * @return
     */
    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 遍历
     * @param code
     * @return
     */
    public static  ResponseCodeEnum getResponseCodeEnum(int code) {
        for (ResponseCodeEnum responseCodeEnum : ResponseCodeEnum.values()) {
            if (responseCodeEnum.getCode() == code) {
                return responseCodeEnum;
            }
        }
        return null;
    }
}
