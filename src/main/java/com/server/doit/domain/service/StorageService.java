package com.server.doit.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class StorageService {
    @Value("classpath:resources/static/")
    private String path;

    public void uploadFile(String fileName, MultipartFile file) {
        if (file.isEmpty()) {
            log.error("Failed to store empty file");
        }

        try {
            String p = "/Users/user/Projects/doit-server/src/main/resources/static";
            Files.copy(file.getInputStream(), Paths.get(path + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error(String.valueOf(e));
            log.error("Failed to store file, File name : " + fileName);
        }
    }
}
