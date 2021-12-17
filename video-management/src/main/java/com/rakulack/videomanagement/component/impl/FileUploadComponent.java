package com.rakulack.videomanagement.component.impl;

import org.springframework.web.multipart.MultipartFile;

/**
 * upload file to server.
 * @author rakulack
 */
public interface FileUploadComponent {
    String uploadFile(MultipartFile file);
}
