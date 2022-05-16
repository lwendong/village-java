package com.village.entity.dto;

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
@ApiModel(value = "EmotionDto", description = "心情功能Dto")
public class EmotionDto extends BaseModel {

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "文件url")
    private String fileUrl;

    @ApiModelProperty(value = "文件类型")
    private String fileType;

    @ApiModelProperty(value = "页码")
    private Long pageNo;

    @ApiModelProperty(value = "每页数据大小")
    private Long pageSize;

}
