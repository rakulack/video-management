package com.rakulack.videomanagement.controller;

import java.io.IOException;
import java.util.Date;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.service.RegisterFileService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

	RegisterFileService registerFileService;

	public UploadController(RegisterFileService registerFileService) {
		this.registerFileService = registerFileService;
	}

	@PostMapping("/upload")
	public Object post(@RequestParam("upload_file") MultipartFile multipartFile,
			@RequestParam("file_date") Date fileDate,
			@AuthenticationPrincipal SimpleLoginUser loginUser, RedirectAttributes redirectAttributes) {
		// ファイルが空の場合は異常終了
		if (multipartFile.isEmpty()) {
			redirectAttributes.addFlashAttribute("alertMessage", "ファイルが登録されていません。");
			return "redirect:/";
		}
		if (!registerFileService.canUpload(multipartFile, loginUser)) {
			redirectAttributes.addFlashAttribute("alertMessage", "同名のファイルが登録されています。");
			return "redirect:/";
		}

		try {
			registerFileService.registerFile(multipartFile, loginUser, fileDate);
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("alertMessage", "ファイルの保存に失敗しました。");
			return "redirect:/";
		}
		redirectAttributes.addFlashAttribute("successMessage", multipartFile.getOriginalFilename() + "をアップロードしました。");
		return "redirect:/";
	}
}