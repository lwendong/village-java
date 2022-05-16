package com.village.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WsVo {

    private String userId;

    private String address;

    private String nick;

    private String avatar;

    private String type;

    private List<String> groupUsers;
}
