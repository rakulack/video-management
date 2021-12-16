package com.rakulack.videomanagement.controller;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.service.RegisterFileService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file/upload")
public class UploadController {

	RegisterFileService registerFileService;
	 
	public UploadController(RegisterFileService registerFileService){
		this.registerFileService = registerFileService;
	}


	public Object post(@RequestParam("upload_file") MultipartFile multipartFile,
    @RequestParam("filetype") String fileType,  // ファイル種類
    @AuthenticationPrincipal SimpleLoginUser loginUser) {
		// ファイルが空の場合は異常終了
        if(multipartFile.isEmpty()){
            // 異常終了時の処理
        }

		return "index";
	}
}