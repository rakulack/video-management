package com.rakulack.videomanagement.service.impl;

import java.io.IOException;
import java.util.Date;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.component.FileCompressComponent;
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
    FileCompressComponent fileCompressComponent;

    public RegisterFileServiceImpl(FileInfoRepository fileInfoRepository, FileUploadComponent fileUploadComponent,
            FileCompressComponent fileCompressComponent) {
        this.fileInfoRepository = fileInfoRepository;
        this.fileUploadComponent = fileUploadComponent;
        this.fileCompressComponent = fileCompressComponent;
    }

    @Transactional
    @Override
    public void registerFile(MultipartFile file, SimpleLoginUser user, Date fileDate) throws IOException {
        try {
            fileUploadComponent.uploadFile(
                    fileCompressComponent.compress(file.getInputStream(), file.getOriginalFilename()),
                    file.getOriginalFilename().replace("mp4", "3gp").replace("MP4", "3gp"), "video/3gp");
            FileInfo fileInfo = new FileInfo(null, user.getUser().getId(),
                    file.getOriginalFilename().replace("mp4", "3gp").replace("MP4", "3gp"), "", fileDate,
                    new Date());
            fileInfoRepository.save(fileInfo);
        } finally {
            fileCompressComponent.deleteTempFile(file.getOriginalFilename());
        }
    }

    @Override
    public boolean canUpload(MultipartFile file, SimpleLoginUser user) {
        return fileInfoRepository.findByUserIdAndFileName(user.getUser().getId(), file.getOriginalFilename()) == null;

    }

}
