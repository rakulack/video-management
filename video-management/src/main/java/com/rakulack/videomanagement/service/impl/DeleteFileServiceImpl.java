package com.rakulack.videomanagement.service.impl;

import java.io.IOException;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.component.FileDeleteComponent;
import com.rakulack.videomanagement.entity.FileInfo;
import com.rakulack.videomanagement.repository.FileInfoRepository;
import com.rakulack.videomanagement.service.DeleteFileService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteFileServiceImpl implements DeleteFileService {

    FileInfoRepository fileInfoRepository;
    FileDeleteComponent fileDeleteComponent;

    public DeleteFileServiceImpl(FileInfoRepository fileInfoRepository, FileDeleteComponent fileDeleteComponent) {
        this.fileInfoRepository = fileInfoRepository;
        this.fileDeleteComponent = fileDeleteComponent;
    }

    @Transactional
    @Override
    public void deleteFile(String fileName, SimpleLoginUser user) throws IOException {
        FileInfo fileInfo = fileInfoRepository.findByUserIdAndFileName(user.getUser().getId(), fileName);
        if (fileInfo != null) {
            fileDeleteComponent.deleteFile(fileName);
            fileInfoRepository.delete(fileInfo);
        }
    }

}
