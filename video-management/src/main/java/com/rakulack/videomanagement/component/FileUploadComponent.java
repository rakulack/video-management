package com.rakulack.videomanagement.component;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * upload file to server.
 * 
 * @author rakulack
 */
public interface FileUploadComponent {
    String uploadFile(MultipartFile file) throws IOException;
}
