package com.village.entity.vo;

import com.village.entity.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "EmotionVo", description = "心情功能VO")
public class EmotionVo extends BaseModel {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "发布用户id")
    private String userId;

    @ApiModelProperty(value = "发布用户昵称")
    private String userNick;

    @ApiModelProperty(value = "发布用户头像url")
    private String userAvatarUrl;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "图片路径")
    private String fileUrl;

    @ApiModelProperty(value = "图片类型")
    private String fileType;

    @ApiModelProperty(value = "当前登录用户是否可以删除当前心情")
    private Boolean canDel = false;

    @ApiModelProperty(value = "心情创建时间")
    private Date createTime;
}
