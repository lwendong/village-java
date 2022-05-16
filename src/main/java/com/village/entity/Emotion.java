package com.village.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName(value = "emotion")
@ApiModel(value = "emotion", description = "心情功能实体类")
public class Emotion extends BaseModel{

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "发布用户id")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "内容")
    @TableField(value = "content")
    private String content;

    @ApiModelProperty(value = "图片路径")
    @TableField(value = "file_url")
    private String fileUrl;

    @ApiModelProperty(value = "图片类型")
    @TableField(value = "file_type")
    private String fileType;

    @ApiModelProperty(value = "心情创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "心情更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "是否删除")
    @TableLogic
    private Boolean deleted;
}
