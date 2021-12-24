package com.rakulack.videomanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ErrorController {
    @GetMapping("/error")
    public String post(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alertMessage", "予期せぬエラーが発生しました。");
        return "redirect:/";
    }

}
