package com.rakulack.videomanagement.component;

import java.io.InputStream;

public interface FileCompressComponent {
    InputStream compress(InputStream is, String fileName);

    void deleteTempFile(String fileName);

}
