package com.rakulack.videomanagement.service;

import java.io.IOException;
import java.io.InputStream;

import com.rakulack.videomanagement.auth.SimpleLoginUser;

public interface FetchFileService {
    InputStream fetchFile(String fileName, SimpleLoginUser user) throws IOException;

}
