package com.village.service;

import com.village.entity.FileUpload;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {

    public String storageFile(MultipartFile file);

    public Resource loadFileAsResource(String fileName);

    public void savaFile(String token, FileUpload upload);
}
