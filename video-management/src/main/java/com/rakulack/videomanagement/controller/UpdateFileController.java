package com.rakulack.videomanagement.controller;

import com.rakulack.videomanagement.auth.SimpleLoginUser;
import com.rakulack.videomanagement.service.UpdateFileInfoService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateFileController {

    UpdateFileInfoService updateFileInfoService;

    public UpdateFileController(UpdateFileInfoService updateFileInfoService) {
        this.updateFileInfoService = updateFileInfoService;
    }

    @PostMapping("update")
    public String post(@RequestParam("fileData") UpdateForm form,
            @AuthenticationPrincipal SimpleLoginUser loginUser, Model model) {
        updateFileInfoService.update(form.getFileName(), loginUser, form.getMemo());
        return "success";
    }

}
