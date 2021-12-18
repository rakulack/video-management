package com.rakulack.videomanagement.service.impl;

import java.io.IOException;
import java.util.Date;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.component.FileUploadComponent;
import com.rakulack.videomanagement.entity.FileInfo;
import com.rakulack.videomanagement.repository.FileInfoRepository;
import com.rakulack.videomanagement.service.RegisterFileService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RegisterFileServiceImpl implements RegisterFileService {

    FileInfoRepository fileInfoRepository;
    FileUploadComponent fileUploadComponent;

    public RegisterFileServiceImpl(FileInfoRepository fileInfoRepository, FileUploadComponent fileUploadComponent) {
        this.fileInfoRepository = fileInfoRepository;
        this.fileUploadComponent = fileUploadComponent;
    }

    @Transactional
    @Override
    public void registerFile(MultipartFile file, SimpleLoginUser user) {
        try {
            String fileName = fileUploadComponent.uploadFile(file);
            FileInfo fileInfo = new FileInfo(null, user.getUser().getId(), fileName, "", new Date());
            fileInfoRepository.save(fileInfo);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
