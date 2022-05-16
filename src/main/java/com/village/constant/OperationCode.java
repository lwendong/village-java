package com.village.constant;

import lombok.Getter;
import lombok.Setter;

public enum OperationCode {

    FAIL(0,"插入数据未成功");

    @Getter
    @Setter
    private final Integer code;
    @Getter
    @Setter
    private final String message;


    OperationCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
