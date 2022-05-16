package com.village.entity.vo;

import com.village.entity.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "UserVo", description = "用户功能VO")
public class UserVo extends BaseModel {

    @ApiModelProperty(value = "用户登录token")
    private String token;

    @ApiModelProperty(value = "操作是否成功")
    private Boolean flag = false;

    @ApiModelProperty(value = "消息")
    private String message;
}
