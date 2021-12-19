package com.rakulack.videomanagement.service;

import java.io.IOException;
import java.io.InputStream;

public interface FetchFileService {
    InputStream fetchFile(String fileName) throws IOException;

}
