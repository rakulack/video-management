package com.rakulack.videomanagement.service.impl;

import java.io.IOException;
import java.io.InputStream;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.component.FileFetchComponent;
import com.rakulack.videomanagement.repository.FileInfoRepository;
import com.rakulack.videomanagement.service.FetchFileService;

import org.springframework.stereotype.Service;

@Service
public class FetchFileServiceImpl implements FetchFileService {

    FileFetchComponent fileFetchComponent;
    FileInfoRepository fileInfoRepository;

    public FetchFileServiceImpl(FileFetchComponent fileFetchComponent, FileInfoRepository fileInfoRepository) {
        this.fileFetchComponent = fileFetchComponent;
        this.fileInfoRepository = fileInfoRepository;
    }

    @Override
    public InputStream fetchFile(String fileName, SimpleLoginUser user) throws IOException {
        // 存在チェック。ログイン後に他人の動画を勝手に見れないようにする。
        if (fileInfoRepository.findbyUserIdAndFileName(user.getUser().getId(), fileName) == null) {
            return null;
        }
        return fileFetchComponent.fetchFile(fileName);
    }

}
