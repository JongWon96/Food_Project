package com.example.Food.controller;

import com.example.Food.domain.UserChange;
import com.example.Food.domain.UserInfo;
import com.example.Food.service.UserChangeService;
import com.example.Food.service.UserInfoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserChangeService userChangeService;


    // 공통된 사용자 세션 체크 메소드
    private UserInfo getUserFromSession(HttpSession session, RedirectAttributes redirectAttributes) {
        UserInfo user = (UserInfo) session.getAttribute("loginUser");
        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인 해주세요.");
        }
        return user;
    }

    // 마이페이지 메인
    @GetMapping
    public String myPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        UserInfo user = getUserFromSession(session, redirectAttributes);
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("userInfo", user);
        return "mypage"; // mypage.html
    }

    // 사용자 정보 수정 페이지
    @GetMapping("/edit-info")
    public String editInfoPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        UserInfo user = getUserFromSession(session, redirectAttributes);
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("userInfo", user);
        return "mypage/edit-info";
    }


    // 사용자 정보 수정 처리
    @PostMapping("/update")
    public String updateUserInfo(@SessionAttribute("loginUser") UserInfo userinfo,
                                 @RequestParam(value = "uweight", required = false) Double uweight,
                                 @RequestParam(value = "uheight", required = false) Double uheight,
                                 @RequestParam(value = "uphone", required = false) String uphone,
                                 @RequestParam(value = "ustyle", required = false) Integer ustyle,
                                 @RequestParam(value = "ugoal", required = false) Integer ugoal,
                                 @RequestParam(value = "uallergy", required = false) Integer uallergy,
                                 RedirectAttributes redirectAttributes, HttpSession session) {


        try {

            // 기존 사용자 정보 백업
            userChangeService.backupUserChange(
                    userinfo.getUuid(),
                    userinfo.getUweight(),
                    userinfo.getUheight()
            );

            UserInfo updatedUser = userInfoService.updateUserInfo (
                    userinfo.getUuid(),
                    uweight != null ? uweight : userinfo.getUweight(),
                    uheight != null ? uheight : userinfo.getUheight(),
                    uphone != null ? uphone : userinfo.getUphone(),
                    uallergy != null ? uallergy : userinfo.getUallergy(),
                    ustyle != null ? ustyle : userinfo.getUstyle(),
                    ugoal != null ? ugoal : userinfo.getUgoal()

            );
            session.setAttribute("loginUser" , updatedUser);



            redirectAttributes.addFlashAttribute("successMessage", "정보가 성공적으로 수정되었습니다.");
            return "redirect:/mypage";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "정보 수정 중 오류가 발생했습니다.");
            return "redirect:/mypage";
        }
    }



    // 비밀번호 변경 페이지
    @GetMapping("/changepassword")
    public String changePassword(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        UserInfo user = getUserFromSession(session, redirectAttributes);
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("userInfo", user);
        return "mypage/changepassword";
    }

    // 비밀번호 변경 처리
    @PostMapping("/changepassword")
    public String updatePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        UserInfo user = (UserInfo) session.getAttribute("loginUser");
        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        try {
            userInfoService.changePassword(user.getUuid(), currentPassword, newPassword, confirmPassword);
            redirectAttributes.addFlashAttribute("successMessage", "비밀번호가 성공적으로 변경되었습니다.");
            return "redirect:/mypage";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/mypage/changepassword";
        }
    }

    // 메인 마이페이지
    @GetMapping("/main/mypage")
    public String mainPage(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("userInfo", user);
        return "mypage/main";
    }


    // 회원 탈퇴 처리
    @PostMapping("/delete")
    public String deleteUser(HttpSession session, RedirectAttributes redirectAttributes) {
        UserInfo user = (UserInfo) session.getAttribute("loginUser");

        if (user == null) {
            return "redirect:/login";
        }

        try {
            userInfoService.deleteUserById(user.getUuid());
            session.invalidate(); // 세션 무효화 (로그아웃 처리)

            redirectAttributes.addFlashAttribute("successMessage", "회원 탈퇴가 완료되었습니다.");
            return "redirect:/main";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "회원 탈퇴 중 오류가 발생했습니다.");
            return "redirect:/mypage";
        }
    }
}