package com.gisquest.plan.service.vo;

/**
 * @author honght
 * @date 2020/9/27 10:13
 */
public enum ResponseCode {
    // 成功
    SUCCESS(200),
    // 失败
    FAIL(400),
    // 未认证
    UNAUTHORIZED(401),
    // 地址不存在
    NOT_FOUND(404),
    // 服务器内部错误
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResponseCode(int code) {
        this.code = code;
    }
}
