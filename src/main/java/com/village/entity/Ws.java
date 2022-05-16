package com.village.entity;

import com.village.websocket.WebSocket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ws {
    private String userId;

    private WebSocket webSocket;

    private String address;

    private String avatar;
}
