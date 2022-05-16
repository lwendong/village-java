package com.village.api;

import com.village.utils.response.FileResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "文件上传下载")
public interface FileControllerApi {

    @Operation(summary = "上传单个文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="file",value="上传的文件",dataTypeClass = MultipartFile.class,
                    paramType = "query",required = true)
    })
    public FileResponse uploadFile(String token,MultipartFile file);

//    @Operation(summary = "上传多个文件")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="file",value="上传的多个文件",dataTypeClass = MultipartFile.class,
//                    paramType = "query",required = true)
//    })
//    public List<FileResponse> uploadFiles(MultipartFile[] files);

    @Operation(summary = "下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileName",value="需要下载的文件名称",dataTypeClass = String.class,
                    paramType = "query",required = true),
            @ApiImplicitParam(name="request",value="请求对象",dataTypeClass = HttpServletRequest.class,
                    paramType = "query",required = false)
    })
    public ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request);
}
