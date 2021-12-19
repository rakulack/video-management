package com.rakulack.videomanagement.service.impl;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.entity.FileInfo;
import com.rakulack.videomanagement.repository.FileInfoRepository;
import com.rakulack.videomanagement.service.UpdateFileInfoService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateFileInfoServiceImpl implements UpdateFileInfoService {

    FileInfoRepository fileInfoRepository;

    public UpdateFileInfoServiceImpl(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    @Transactional
    @Override
    public void update(String fileName, SimpleLoginUser user, String memo) {
        FileInfo fileInfo = fileInfoRepository.findbyUserIdAndFileName(user.getUser().getId(), fileName);
        fileInfo.setMemo(memo);
        fileInfoRepository.save(fileInfo);
    }
}
