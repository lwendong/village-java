package com.village.constant;

import lombok.Getter;
import lombok.Setter;

public enum WebSocketType {
    /**
     * 成功:200
     */
    LIST("list"),
    GROUP("group"),
    CHAT("chat"),
    RECEIVE("receive"),
    SEND("send"),
    GROUP_RECEIVE("groupReceive"),
    GROUP_SEND("groupSend");

    @Getter
    @Setter
    private final String value;

    WebSocketType(String value) {
        this.value = value;
    }
}
