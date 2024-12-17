package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Board;
import com.example.demo.domain.UserInfo;
import com.example.demo.dto.UserVo;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentsService;
import com.example.demo.service.UserInfoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class loginController {

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentsService commentsService;

	
	/// 회원가입 임시

	@GetMapping("/login")
	public String LoginPage() {
		// 로그인 페이지를 보여주는 메소드 (여기서는 임시 로그인 사용)
		return "board/login"; // 임시 로그인 페이지로 이동
	}
	
	@GetMapping("/user_login_form")
	public String LoginPage2() {
		// 로그인 페이지를 보여주는 메소드 (여기서는 임시 로그인 사용)
		return "board/user_login_form"; // 임시 로그인 페이지로 이동
	}

//	@PostMapping("/login")
//	public String temporaryLogin(HttpSession session, Model model) {
//	    // 임시 사용자 정보 설정 (예: 사용자 이름, ID, 비밀번호 등 설정)
//	    UserInfo temporaryUser = new UserInfo();
//	    
//	    
//
//	    
//	    temporaryUser.setUid("user3"); // 임시 ID
//	    temporaryUser.setUpw("password1234"); // 임시 비밀번호
//	    temporaryUser.setUname("춘삼이"); // 임시 이름
//	    temporaryUser.setUphone(123442890); // 임시 전화번호
//	    temporaryUser.setUgender(2); // 임시 성별 (예: 0 = 남성, 1 = 여성)
//	    temporaryUser.setUage(30); // 임시 나이
//	    temporaryUser.setUweight(70); // 임시 체중
//	    temporaryUser.setUheight(175); // 임시 키
//	    temporaryUser.setUgoal(65); 
//	    temporaryUser.setUstyle(2);
//	    temporaryUser.setUallergy("없음"); // 임시 알레르기 정보
//
//	    // UserInfoRepository를 통해 사용자 정보 저장 (임시 사용자 데이터)
//	    userInfoService.createUserInfo(temporaryUser); // DB에 저장되고 자동 생성된 uuid가 할당됩니다.
//
//	    // 세션에 임시 사용자 정보 저장
//	    session.setAttribute("loginUser", temporaryUser);
//	    System.out.print("temporaryUser: " + temporaryUser);  // 임시 사용자 정보 출력
//
//	    // 임시 로그인 후 리디렉션 처리
//	    return "redirect:/boards"; // 임시 로그인 후 리디렉션할 페이지
//	}
	@PostMapping("/login")
	public String login(HttpSession session, Model model, HttpServletRequest request) {
	    // 이미 로그인된 상태인지 확인
	    if (session.getAttribute("loginUser") != null) {
	        return "redirect:/boards";  // 이미 로그인된 경우 게시판 페이지로 리디렉션
	    }

	    // 로그인 시 사용자가 입력한 ID와 비밀번호 (예시: 폼 데이터에서 받는 값)
	    String uid = "user3"; // 예시, 실제로는 폼 데이터에서 받는 값
	    String upw = "password1234"; // 예시, 실제로는 폼 데이터에서 받는 값

	    // DB에서 사용자 정보를 조회
	    UserInfo existingUser = userInfoService.getUserInfoByUId(uid);

	    // 사용자가 존재하고, 비밀번호가 일치하는지 확인
	    if (existingUser != null && existingUser.getUpw().equals(upw)) {
	        // 비밀번호가 일치하면 세션에 사용자 정보 저장
	        session.setAttribute("loginUser", existingUser);

	        // 이전 페이지 URL을 가져오기
	        String referer = request.getHeader("Referer");

	        // 만약 Referer가 null이 아니면, 그 URL로 리디렉션
	        if (referer != null && !referer.contains("login")) {  // 로그인 페이지를 제외한 경우
	            return "redirect:" + referer;
	        } else {
	            // Referer가 없거나 로그인 페이지일 경우 기본 페이지로 리디렉션
	            return "redirect:/boards";
	        }
	    } else {
	        // 사용자가 없거나 비밀번호가 틀린 경우
	        model.addAttribute("errorMessage", "아이디 또는 비밀번호가 틀립니다.");
	        return "board/login"; // 로그인 페이지로 돌아가도록
	    }
	}


	

	

	// 로그아웃 요청 처리
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 세션에서 로그인된 사용자 정보 삭제
		session.removeAttribute("loginUser");
		//어드민 - 여기작성하는건지는 의문------------------
		session.removeAttribute("adminUser");

		// 로그아웃 후 게시판 목록 페이지로 리다이렉트
		return "redirect:/boards";
	}


}