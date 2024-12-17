package com.example.Food.controller;


import com.example.Food.domain.UserInfo;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainPageController {

    @GetMapping("/main")
    public String ShowmainPage(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        model.addAttribute("user", user);
        return "main"; // resources/templates/main.html
    }


    @PostMapping("/logout")
    public String logout(HttpSession session , RedirectAttributes redirectAttributes) {
        // 세션 무효화
        session.invalidate();
        redirectAttributes.addFlashAttribute("successMessage", "로그아웃이 성공적으로 완료되었습니다.");
        return "redirect:/main";
    }
}

