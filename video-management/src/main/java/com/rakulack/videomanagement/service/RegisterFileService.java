package com.rakulack.videomanagement.service;

import org.springframework.web.multipart.MultipartFile;

public interface RegisterFileService {
    void registerFile(MultipartFile file);
}
