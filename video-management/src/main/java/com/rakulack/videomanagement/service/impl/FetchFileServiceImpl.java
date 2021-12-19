package com.rakulack.videomanagement.service.impl;

import java.io.IOException;
import java.io.InputStream;

import com.rakulack.videomanagement.component.FileFetchComponent;
import com.rakulack.videomanagement.service.FetchFileService;

public class FetchFileServiceImpl implements FetchFileService {

    FileFetchComponent fileFetchComponent;

    public FetchFileServiceImpl(FileFetchComponent fileFetchComponent) {
        this.fileFetchComponent = fileFetchComponent;
    }

    @Override
    public InputStream fetchFile(String fileName) throws IOException {
        return fileFetchComponent.fetchFile(fileName);
    }

}
