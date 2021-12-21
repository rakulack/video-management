package com.rakulack.videomanagement.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.entity.FileInfo;
import com.rakulack.videomanagement.repository.FileInfoRepository;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDataController {

    private FileInfoRepository fileInfoRepository;

    public FileDataController(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    @GetMapping("/fileData")
    public List<FileInfo> getFileData(@AuthenticationPrincipal SimpleLoginUser loginUser) {
        return fileInfoRepository.findByUserIdOrderByPrcDateDesc(loginUser.getUser().getId());
    }

    @GetMapping("/fileData/{YYYY}")
    public List<FileInfo> getFileDataWithYear(@AuthenticationPrincipal SimpleLoginUser loginUser,
            @PathVariable("YYYY") String yyyy) {
        try {
            String.valueOf(Integer.valueOf(yyyy).intValue() + 1);
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            Date sdate = sdFormat.parse(yyyy + "/04/01 00:00:00");
            Date edate = sdFormat.parse(String.valueOf(Integer.valueOf(yyyy).intValue() + 1) + "/3/31 23:59:59");
            return fileInfoRepository.findByUserIdAndFileDateBetweenOrderByFileDateDesc(loginUser.getUser().getId(),
                    sdate, edate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
