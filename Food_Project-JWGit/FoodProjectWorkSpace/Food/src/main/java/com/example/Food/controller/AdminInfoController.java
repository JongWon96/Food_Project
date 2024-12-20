package com.example.Food.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.Food.domain.AdminInfo;
import com.example.Food.domain.Board;
import com.example.Food.domain.UserInfo;
import com.example.Food.service.AdminInfoService;
import com.example.Food.service.BoardService;
import com.example.Food.service.CommentsService;
import com.example.Food.service.UserInfoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@SessionAttributes("admininfoUser")
public class AdminInfoController {
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentsService commentsService;


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
	public String admininfoLogin(AdminInfo vo, HttpSession session, Model model) {

		int result = admininfoService.adminCheck(vo);
		if (result == 1) { // 정상 사용자
			AdminInfo admininfo = admininfoService.getAdminInfo(vo.getAid());
			model.addAttribute("admininfoUser", admininfo); // 세션에 저장
			System.out.println("admininfoUser" + admininfo);
			return "redirect:/admininfo_userinfo_list";

		} else {
			model.addAttribute("message", "아이디 또는 비밀번호가 맞지 않습니다.");
			return "admininfo/main";
		}
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
			@RequestParam(value = "key", defaultValue = "") String uname,
			Model model) {
		List<UserInfo> userinfoList = userinfoService.getUserInfoList(uname);
		model.addAttribute("userinfoList", userinfoList);

		return "admininfo/userinfo/userinfoList";
	}

	//-----------------------------------------------------어드민 기능?
	// 신고당한 게시글만 보기
	@GetMapping("/admin/boards")
	public String AdminUser(HttpSession session, Model model) {

		// 어드민 로그인 여부 확인


		// 신고당한 게시글 조회
		List<Board> dangerBoards = boardService.getDangerBoards(); // 신고된 게시글 목록 가져오기

		// 모델에 데이터 전달
		model.addAttribute("dangerBoards", dangerBoards);

		// 뷰 페이지로 이동
		return "board/DangerList";
	}

	//게시글 승인 처리
	@GetMapping("/admin/board/approve/{buid}")
	public String approveBoard(HttpSession session, @PathVariable("buid") Integer buid,
							   RedirectAttributes redirectAttributes) {
		AdminInfo admin = (AdminInfo) session.getAttribute("admininfoUser");

		if (admin == null) {
			return "redirect:/admin/boards";
		}
		try {
			boardService.approveBoard(buid);
			return "redirect:/admin/boards";
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "board/DangerList";
	}


	// 게시글 삭제 처리
	@GetMapping("/admin/board/remove/{buid}")
	public String removeBoard(HttpSession session, @PathVariable("buid") Integer buid,
							  RedirectAttributes redirectAttributes) {
		AdminInfo admin = (AdminInfo) session.getAttribute("admininfoUser");
		if (admin == null) {
			return "redirect:/admin/boards";
		}
		try {
			boardService.deleteBoard(buid); // 게시글 삭제
			return "redirect:/admin/boards";
		} catch (RuntimeException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}

		return "board/DangerList";
	}

}





