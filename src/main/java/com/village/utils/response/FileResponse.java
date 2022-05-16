package com.village.utils.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "FileResponse", description = "文件上传下载响应类")
public class FileResponse implements Serializable {

    @ApiModelProperty(value = "文件名称")
    private String fileName;
    @ApiModelProperty(value = "文件显示地址")
    private String fileShowUrl;
    @ApiModelProperty(value = "文件类型")
    private String fileType;
    @ApiModelProperty(value = "文件大小")
    private long size;

    public String getSize() {
        long round = Math.round(size / 1024.00);
        return round > 0 ? round + "KB" : "不足1KB";
    }
}
