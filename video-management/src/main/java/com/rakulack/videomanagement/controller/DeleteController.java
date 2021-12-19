package com.rakulack.videomanagement.controller;

import java.io.IOException;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.service.DeleteFileService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

public class DeleteController {

    DeleteFileService deleteFileService;

    public DeleteController(DeleteFileService deleteFileService) {
        this.deleteFileService = deleteFileService;
    }

    public String post(@RequestParam("fileData") UpdateForm form,
            @AuthenticationPrincipal SimpleLoginUser loginUser, Model model) {
        try {
            deleteFileService.deleteFile(form.getFileName(), loginUser);
        } catch (IOException e) {
            e.printStackTrace();
            return "index";
        }
        model.addAttribute("successMessage", form.getFileName() + "を削除しました。");
        return "index";

    }
}
