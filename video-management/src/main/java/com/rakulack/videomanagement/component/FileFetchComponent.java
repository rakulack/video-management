package com.rakulack.videomanagement.component;

import java.io.IOException;
import java.io.InputStream;

public interface FileFetchComponent {
    InputStream fetchFile(String fileName) throws IOException;
}
