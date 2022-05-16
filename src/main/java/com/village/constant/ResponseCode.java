package com.village.constant;

import lombok.Getter;
import lombok.Setter;

public enum ResponseCode {

    /**
     * 成功:200
     */
    SUCCESS(200, "成功"),
    /**
     * 失败 -1
     */
    FAIL(-1, "失败"),
    /**
     * 参数错误：1001-1999
     */
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_NULL(1002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),
    /**
     * 用户错误：2001-2999
     */
    USER_NOT_LOGIN_IN(2001, "用户未登录，访问的路径需要验证，请登录"),
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003, "账号被禁用"),
    USER_NOT_EXIST(2004, "用户不存在"),
    USER_HAS_EXISTED(2005, "用户已存在");


    @Getter
    @Setter
    private final Integer code;
    @Getter
    @Setter
    private final String message;


    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
