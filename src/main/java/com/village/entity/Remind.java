package com.village.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "remind")
@ApiModel(value = "remind", description = "提醒功能实体类")
public class Remind extends BaseModel{

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "发布用户id")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "内容")
    @TableField(value = "content")
    private String content;

    @ApiModelProperty(value = "颜色")
    @TableField(value = "color")
    private String color;

    @ApiModelProperty(value = "开始时间")
    @TableField(value = "start_time")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    @TableField(value = "end_time")
    private Date endTime;

    @ApiModelProperty(value = "结束时间")
    @TableField(exist = false)
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private String endTimeString;

    @ApiModelProperty(value = "是否完成")
    @TableField(value = "finish")
    private Boolean finish;


    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    @ApiModelProperty(value = "是否删除")
    @TableLogic
    private Boolean deleted;


}
