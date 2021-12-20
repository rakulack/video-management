package com.rakulack.videomanagement.controller;

import java.io.IOException;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.service.DeleteFileService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DeleteController {

    DeleteFileService deleteFileService;

    public DeleteController(DeleteFileService deleteFileService) {
        this.deleteFileService = deleteFileService;
    }

    @PostMapping("/delete")
    public String post(@RequestParam("delete_file") String fileName,
            @AuthenticationPrincipal SimpleLoginUser loginUser, RedirectAttributes redirectAttributes) {
        try {
            deleteFileService.deleteFile(fileName, loginUser);
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("alertMessage", "ファイルの削除に失敗しました。");
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("successMessage", fileName + "を削除しました。");
        return "redirect:/";

    }
}
