package com.village.entity;

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
@TableName(value = "file_upload")
@ApiModel(value = "fileUpload", description = "文件上传存储实体类")
public class FileUpload extends BaseModel{

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "文件名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "文件类型")
    @TableField(value = "type")
    private String type;

    @ApiModelProperty(value = "文件url")
    @TableField(value = "url")
    private String url;

    @ApiModelProperty(value = "上传文件用户名")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "文件创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
}
