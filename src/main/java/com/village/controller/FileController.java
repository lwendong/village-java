package com.village.controller;

import com.village.annotation.PassLoginVerify;
import com.village.api.FileControllerApi;
import com.village.config.ServerConfig;
import com.village.entity.FileUpload;
import com.village.service.FileService;
import com.village.utils.response.FileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@RestController
public class FileController implements FileControllerApi {


    @Autowired
    private FileService fileService;

    @Autowired
    private ServerConfig serverConfig;

    @Value("${file.upload.path}")
    private String fileStorageUrl;

    @Override
    @PassLoginVerify
    @PostMapping("/uploadFile")
    public FileResponse uploadFile(@RequestHeader("Authorization") String token, MultipartFile file) {
        String fileName = fileService.storageFile(file);
        String fileShowUrl = null;
        String fileType = null;
        if(Objects.requireNonNull(file.getContentType()).contains("image")){
            fileType = "img";
            fileShowUrl = serverConfig.getUrl() + "/image/" + fileName;
        }else if(file.getContentType().contains("video")){
            fileType = "video";
            fileShowUrl = serverConfig.getUrl() + "/video/" + fileName;
        }else{
            fileType = "file";
            fileShowUrl = serverConfig.getUrl() + "/file/" + fileName;
        }
        FileUpload build = FileUpload.builder().url(fileShowUrl).name(fileName).type(fileType).build();
        fileService.savaFile(token,build);
        return new FileResponse(fileName, fileShowUrl,
                fileType, file.getSize());
    }

//    @Override
//    @PassLoginVerify
//    @PostMapping("/uploadFiles")
//    public List<FileResponse> uploadFiles(MultipartFile[] files) {
//        return Arrays.stream(files)
//                .map(this::uploadFile)
//                .collect(Collectors.toList());
//    }

    @Override
    @PassLoginVerify
    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileService.loadFileAsResource(fileName);
        try {
            request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
