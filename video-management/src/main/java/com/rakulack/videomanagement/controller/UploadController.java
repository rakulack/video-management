package com.rakulack.videomanagement.controller;

import java.io.IOException;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.service.RegisterFileService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	RegisterFileService registerFileService;

	public UploadController(RegisterFileService registerFileService) {
		this.registerFileService = registerFileService;
	}

	@PostMapping("upload")
	public Object post(@RequestParam("upload_file") MultipartFile multipartFile,
			@AuthenticationPrincipal SimpleLoginUser loginUser, Model model) {
		// ファイルが空の場合は異常終了
		if (multipartFile.isEmpty()) {
			return "index";
		}
		try {
			registerFileService.registerFile(multipartFile, loginUser);
		} catch (IOException e) {
			e.printStackTrace();
			return "index";
		}
		model.addAttribute("successMessage", multipartFile.getOriginalFilename() + "をアップロードしました。");
		return "index";
	}
}