package com.rakulack.videomanagement.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VideoErrorController implements ErrorController {
    @RequestMapping("/error")
    public String post(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alertMessage", "予期せぬエラーが発生しました。");
        return "redirect:/";
    }

}
