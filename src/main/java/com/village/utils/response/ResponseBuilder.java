package com.village.utils.response;

import com.village.constant.ResponseCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ResponseBuilder {
    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Response success() {
        return Response.builder()
                .code(ResponseCode.SUCCESS.getCode())
                .message(ResponseCode.SUCCESS.getMessage())
                .build();
    }

    /**
     * 返回成功消息
     *
     * @param message 返回内容
     * @return 成功消息
     */
    public static  <T> Response<T> success(String message) {
        return Response.<T>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .message(message)
                .build();
    }

    /**
     * 返回成功消息
     *
     * @param data 数据对象
     * @return 成功消息
     */
    public static <T> Response<T> success(T data) {
        return Response.<T>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .message(ResponseCode.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    /**
     * 返回成功消息
     *
     * @param responseCode 状态码,返回内容
     * @return 成功消息
     */
    public static <T> Response<T> success(ResponseCode responseCode) {
        return Response.<T>builder()
                .code(responseCode.getCode())
                .message(responseCode.getMessage())
                .build();
    }

    /**
     * 返回成功消息
     *
     * @param ResponseCode 状态码,返回内容
     * @param data       数据对象
     * @return 成功消息
     */
    public static <T> Response<T> success(ResponseCode ResponseCode, T data) {
        return Response.<T>builder()
                .code(ResponseCode.getCode())
                .message(ResponseCode.getMessage())
                .data(data)
                .build();
    }


    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static Response errorInfo() {
        return Response.builder()
                .code(ResponseCode.FAIL.getCode())
                .message(ResponseCode.FAIL.getMessage())
                .build();
    }

    /**
     * 根据枚举返回错误消息
     *
     * @param ResponseCode 状态码 返回内容
     * @return 错误消息
     */
    public static Response errorInfo(ResponseCode ResponseCode) {
        return Response.builder()
                .code(ResponseCode.getCode())
                .message(ResponseCode.getMessage())
                .build();
    }

    /**
     * 自定义错误返回错误消息
     *
     * @param ResponseCode 状态码 返回内容
     * @param data       返回的数据
     * @return 错误消息
     */
    public static <T> Response<T> errorInfo(ResponseCode ResponseCode, T data) {
        return Response.<T>builder()
                .code(ResponseCode.getCode())
                .message(ResponseCode.getMessage())
                .data(data)
                .build();
    }

}
