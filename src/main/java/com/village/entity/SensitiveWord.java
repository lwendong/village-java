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
@TableName(value = "sensitive_word")
@ApiModel(value = "Sensitive", description = "敏感词功能实体类")
public class SensitiveWord extends BaseModel{

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "原始文本")
    @TableField(value = "original_text")
    private String originalText;

    @ApiModelProperty(value = "替换后文本")
    @TableField(value = "replace_text")
    private String replaceText;

    @ApiModelProperty(value = "触发敏感词的用户")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private Date create_time;
}
