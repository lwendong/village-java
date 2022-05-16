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
@TableName(value = "note")
@ApiModel(value = "Note", description = "便笺功能实体类")
public class Note extends BaseModel {

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "便笺标题")
    @TableField(value = "title")
    private String title;

    @ApiModelProperty(value = "便笺内容")
    @TableField(value = "content")
    private String content;

    @ApiModelProperty(value = "便笺分组")
    @TableField(value = "note_group")
    private String noteGroup;

    @ApiModelProperty(value = "便笺编号")
    @TableField(value = "note_number")
    private String noteNumber;

    @ApiModelProperty(value = "便笺所属用户id")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "便笺背景颜色")
    @TableField(value = "color")
    private String color;

    @ApiModelProperty(value = "便笺创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "便笺更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "是否删除")
    @TableLogic
    private Integer deleted;

}
