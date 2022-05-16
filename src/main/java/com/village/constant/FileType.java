package com.village.constant;

import lombok.Getter;
import lombok.Setter;

public enum FileType {
    /**
     * 成功:200
     */
    IMAGE("image/jpeg");


    @Getter
    @Setter
    private final String value;

    FileType(String value) {
        this.value = value;
    }
}
