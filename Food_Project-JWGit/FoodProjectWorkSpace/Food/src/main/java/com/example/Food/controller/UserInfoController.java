package com.example.Food.controller;

import com.example.Food.domain.UserInfo;
import com.example.Food.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("loginUser")
public class UserInfoController {
	@Autowired
	private UserInfoService userinfoService;

	// 로그인 표시
	@GetMapping("/login")
	public String loginView() {

		return "userinfo/login";
	}

	// 사용자 로그인
	@PostMapping("/login")
	public String loginAction(UserInfo vo,
							  Model model) {
		String url = "";

		if (userinfoService.loginUid(vo) == 1) { // 정상 사용자
			// 사용자 정보를 페이지 내장 객체와 세션 내장객체에 저장
			model.addAttribute("loginUser", userinfoService.getUserInfo(vo.getUid()));
			System.out.println("userinfoService.getUserInfo(vo.getUid())" + userinfoService.getUserInfo(vo.getUid()));

			url = "redirect:/main";
		} else {
			url = "userinfo/login_fail";
		}

		return url;
	}

	// 약정화면 표시
	@GetMapping("/contract")
	public String contractView() {

		return "userinfo/contract";
	}

	// 회원가입 화면 표시
	@GetMapping("/join_form")
	public String joinView() {

		return "userinfo/join";
	}


	// ID중복 확인 처리
	@GetMapping("/id_check_form")
	public String idCheckView(UserInfo vo, Model model) {
		// confirmID()를 호출하여 id존재 확인 결과 저장
		// result 결과 1이면 id존재, -1이면 id존재하지 않음.
		int result = userinfoService.confirmUid(vo.getUid());

		// confirmID()의 결과를 model 객체에 저장
		model.addAttribute("message", result);
		model.addAttribute("uid", vo.getUid());

		// idcheck 화면 호출
		return "userinfo/idcheck";
	}

	// 회원가입 처리
	@PostMapping("/join")
	public String joinAction(UserInfo vo) {
		System.out.println("회원가입: vo=" + vo);
		userinfoService.insertUserInfo(vo);

		return "userinfo/login";
	}

	// 아이디 찾기 화면 표시
	@GetMapping("/find_id_form")
	public String findIdView() {

		return "userinfo/findId";
	}

	// 비밀번호 찾기 화면 표시
	@GetMapping("/find_pwd_form")
	public String findPwdView() {

		return "userinfo/findPassword";
	}

	// 아이디 찾기 처리
	@PostMapping("/find_id")
	public String findIdAction(UserInfo vo, Model model) {
		UserInfo userinfo = userinfoService.getUidByUnameAndUphone(vo.getUname(), vo.getUphone());

		if(userinfo != null) {  // 아이디 조회 성공
			model.addAttribute("message", 1);
			model.addAttribute("uid", userinfo.getUid());
		} else {
			model.addAttribute("message", -1);
		}

		return "userinfo/findResult";
	}


	// 비밀번호 찾기 처리
	@PostMapping("/find_pwd")
	public String findPwdAction(UserInfo vo, Model model) {
		// 화면에서 입력한 id, name, phone을 조건으로 비밀번호 찾기 서비스 호출
		UserInfo userinfo = userinfoService.getUpwByUidUnameUphone(vo.getUid(), vo.getUname(), vo.getUphone());

		if(userinfo != null) {  // 사용자 조회 성공
			model.addAttribute("message", 1);
			model.addAttribute("uid", userinfo.getUid());
		} else {
			model.addAttribute("message", -1);
		}

		return "userinfo/findPwdResult";
	}
	/*
	 * 비밀번호 변경 처리
	 */
	@PostMapping("change_pwd")
	public String changePwd(UserInfo vo) {
		userinfoService.changePassword(vo);

		return "userinfo/changePwdOk";
	}

}
