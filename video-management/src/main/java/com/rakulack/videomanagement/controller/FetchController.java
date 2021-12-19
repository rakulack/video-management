package com.rakulack.videomanagement.controller;

import java.io.IOException;
import java.io.InputStream;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.service.FetchFileService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FetchController {

    FetchFileService fetchFileService;

    public FetchController(FetchFileService fetchFileService) {
        this.fetchFileService = fetchFileService;
    }

    @GetMapping(value = "/fetch/{fileName}")
    public InputStream get(@PathVariable("fileName") String fileName,
            @AuthenticationPrincipal SimpleLoginUser loginUser) {
        try {
            return fetchFileService.fetchFile(fileName, loginUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
