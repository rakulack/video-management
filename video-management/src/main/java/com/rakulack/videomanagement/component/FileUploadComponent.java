package com.rakulack.videomanagement.component;

import java.io.IOException;
import java.io.InputStream;

/**
 * upload file to server.
 * 
 * @author rakulack
 */
public interface FileUploadComponent {
    void uploadFile(InputStream is, String fileName, String contentType) throws IOException;
}
