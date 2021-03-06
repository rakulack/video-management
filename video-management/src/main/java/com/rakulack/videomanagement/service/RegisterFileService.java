package com.rakulack.videomanagement.service;

import java.io.IOException;
import java.util.Date;

import com.rakulack.videomanagement.auth.SimpleLoginUser;

import org.springframework.web.multipart.MultipartFile;

/**
 * Register video file to server and save data.
 * 
 * @author rakulack
 */
public interface RegisterFileService {
    void registerFile(MultipartFile file, SimpleLoginUser user, Date fileDate) throws IOException;

    boolean canUpload(MultipartFile file, SimpleLoginUser user);
}
