package com.example.Food.controller;

import com.example.Food.domain.UserInfo;
import com.example.Food.persistence.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;



    @GetMapping
    public String loginPage() {

        return "login"; // login.html 파일을 반환
    }

    @PostMapping
    public String login(@RequestParam("userid") String userid,
                        @RequestParam("userpw") String userpw,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {

        // 사용자 인증 로직
        UserInfo user = userRepository.findByUidAndUpw(userid, userpw);

        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/main"; // 로그인 성공 시 메인페이지로 이동
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "redirect:/login"; // 로그인 실패 시 다시 로그인 페이지로 이동

        }


    }

    }