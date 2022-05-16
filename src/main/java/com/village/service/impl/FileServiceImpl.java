package com.village.service.impl;

import com.village.entity.FileUpload;
import com.village.exception.FileException;
import com.village.mapper.FileUploadMapper;
import com.village.service.FileService;
import com.village.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileUploadMapper fileUploadMapper;

    private final Path fileStorageLocationPath; // 文件在本地存储的地址

    public FileServiceImpl(@Value("${file.upload.path}") String path) {
        this.fileStorageLocationPath = Paths.get(path).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocationPath);
        } catch (IOException e) {
            throw new FileException("不能创建目录", e);
        }
    }

    @Override
    public String storageFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            String[] split = fileName.split("\\.");
            fileName = UUID.randomUUID().toString().replace("-", "") +"."+ split[split.length -1];
            Path targetPath = this.fileStorageLocationPath.resolve(fileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new FileException("无法保存文件： " + fileName + ". 请重试", e);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocationPath.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileException("文件不不存在" + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("未找到文件" + fileName, ex);
        }
    }

    @Override
    public void savaFile(String token, FileUpload upload) {
        String userId = TokenUtil.getClaimByName(token, "userId").asString();
        upload.setUserId(userId);
        fileUploadMapper.insert(upload);
    }
}
