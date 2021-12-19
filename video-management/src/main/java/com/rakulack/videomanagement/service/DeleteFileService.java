package com.rakulack.videomanagement.service;

import java.io.IOException;

import com.rakulack.videomanagement.auth.SimpleLoginUser;

public interface DeleteFileService {
    void deleteFile(String fileName, SimpleLoginUser user) throws IOException;
}
