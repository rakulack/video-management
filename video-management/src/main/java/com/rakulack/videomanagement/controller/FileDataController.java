package com.rakulack.videomanagement.controller;

import java.util.List;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.entity.FileInfo;
import com.rakulack.videomanagement.repository.FileInfoRepository;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDataController {

    private FileInfoRepository fileInfoRepository;

    public FileDataController(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    @GetMapping("/fileData")
    public List<FileInfo> getFileData(@AuthenticationPrincipal SimpleLoginUser loginUser) {
        return fileInfoRepository.findByUserIdOrderByPrcDateDesc(loginUser.getUser().getId());
    }
}
