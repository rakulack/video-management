package com.rakulack.videomanagement.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.service.FetchFileService;

import org.apache.commons.io.IOUtils;
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
    public void get(@PathVariable("fileName") String fileName,
            @AuthenticationPrincipal SimpleLoginUser loginUser, HttpServletResponse response) {
        try {
            InputStream is = fetchFileService.fetchFile(fileName, loginUser);
            IOUtils.copy(is, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
