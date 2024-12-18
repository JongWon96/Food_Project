package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.domain.AdminInfo;
import com.example.demo.domain.UserInfo;
import com.example.demo.service.AdminInfoService;
import com.example.demo.service.UserInfoService;

@Controller
@SessionAttributes("admininfoUser")
public class AdminInfoController {

	@Autowired
	private AdminInfoService admininfoService;

	@Autowired
	private UserInfoService userinfoService;
	
	@GetMapping("/admin_login_form")
	public String adminLoginView() {
		
		return "admininfo/main";
	}
	
	// 관리자 로그인 구현
	@PostMapping("/admin_login")
	public String admininfoLogin(AdminInfo vo, Model model) {
		String url = "";
		List<UserInfo> ulist = new ArrayList<UserInfo>();
		
		// (1) 관리자 계정 인증 호출: adminCheck()
		int result = admininfoService.adminCheck(vo);
		
		// (2) 인증 결과에 따라 
		// -- 정상 사용자이면 회원 목록 출력(admininfo/userinfo/userinfoList.html) (url: admininfo_userinfo_list)
		//    관리자 정보를 세션에 저장: "admininfoUser" 속성에 저장
		// -- 비정상 사용자이면 관리자 로그인 화면 출력 
		if (result == 1) {  // 정상 사용자
			AdminInfo admininfo = admininfoService.getAdminInfo(vo.getAid());
			System.out.println("admininfoLogin: " + admininfo);
			
			model.addAttribute("admininfoUser", admininfo);
			
			url = "redirect:/admininfo_userinfo_list";
		} else {
			model.addAttribute("message", "아이디 또는 비밀번호가 맞지 않습니다.");
			url = "admininfo/main";
		}
		
		return url;
	}
	
	@GetMapping("/admin_logout")
	public String admininfoLogout(SessionStatus status) {
		
		status.setComplete();  // 세션 종료
		
		return "admininfo/main";
	}
	
	/*
	 * 회원 전체목록 조회 처리
	 */
	@RequestMapping("/admininfo_userinfo_list")
	public String admininfoUserInfoList(
			@RequestParam(value="key", defaultValue="") String uname,
			Model model) {
		List<UserInfo> userinfoList = userinfoService.getUserInfoList(uname);
		model.addAttribute("userinfoList", userinfoList);
		
		return "admininfo/userinfo/userinfoList";
	}
}

