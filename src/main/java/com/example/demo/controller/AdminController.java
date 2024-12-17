//package com.example.demo.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.example.demo.domain.Board;
//import com.example.demo.domain.UserInfo;
//import com.example.demo.dto.UserVo;
//import com.example.demo.service.BoardService;
//import com.example.demo.service.CommentsService;
//import com.example.demo.service.UserInfoService;
//
//import jakarta.servlet.http.HttpSession;
//@Controller
//public class AdminController {
//
////	@Autowired
////	private AdminInfoService adminInfoService;
//	@Autowired
//	private UserInfoService userInfoService;
//	@Autowired
//	private BoardService boardService;
//	@Autowired
//	private CommentsService commentsService;
//
//
//
//	//-----------------------------------------------------어드민 기능?
//		// 신고당한 게시글만 보기
//		@GetMapping("/admin/boards")
//		public String AdminUser(HttpSession session, Model model) {
//			 AdminInfo admin = (AdminInfo) session.getAttribute("AdminUser");
//			
//			// 어드민 로그인 여부 확인
//			if (admin == null) {
//				return "boards";
//			}
//
//			// 신고당한 게시글 조회
//			List<Board> dangerBoards = boardService.getDangerBoards(); // 신고된 게시글 목록 가져오기
//
//			// 모델에 데이터 전달
//			model.addAttribute("dangerBoards", dangerBoards);
//
//			// 뷰 페이지로 이동
//			return "board/DangerList";
//		}
//		
//		@GetMapping("/admin/board/approve/{buid}")
//		public String approveBoard(HttpSession session,@PathVariable("buid")Integer buid,
//				RedirectAttributes redirectAttributes) {
//			AdminInfo admin = (AdminInfo) session.getAttribute("AdminUser");
//				
//		if (admin == null) {
//			return "boards"; // 로그인되지 않은 경우 게시판 목록 페이지로 이동
//		}
//		try {
//			boardService.approveBoard(buid);
//			return "redirect:/admin/boards"; 
//		}catch(RuntimeException e) {
//			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
//}
//		return "board/DangerList";
//}
//
//		
//		
//		// 게시글 삭제 처리
//		@GetMapping("/admin/board/remove/{buid}")
//		public String removeBoard(HttpSession session, @PathVariable("buid") Integer buid,
//				RedirectAttributes redirectAttributes) {
//			AdminInfo admin = (AdminInfo) session.getAttribute("AdminUser");
//
//			// 어드민 로그인 여부 확인
//			if (admin == null) {
//				return "boards"; // 로그인되지 않은 경우 게시판 목록 페이지로 이동
//			}
//
//			try {
//				boardService.deleteBoard(buid); // 게시글 삭제
//				return "redirect:/admin/boards"; 
//			} catch (RuntimeException e) {
//				redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
//			}
//
//			return "board/DangerList"; 
//		}
//		@GetMapping("/board_insert_form")
//		public String BoardWriteForm(HttpSession session, Model model) {
//
//			AdminInfo admin = (AdminInfo) session.getAttribute("AdminUser");
//			AdminVo adminVo = new AdmonVo();
//			if (admin == null) {
//				model.addAttribute("msg", "로그인 후 이용해주세요.");
//				model.addAttribute("redirectTo", "/user_login_form");
//				return "board/alert";
//			} else {
//				model.addAttribute("AdminVo", adminVo);
//				return "board/boardInsert";
//			}
//		}
//
//
//
//	// 게시글 작성하기
//		@PostMapping("/createBoard")
//		public String createBoard(HttpSession session, @RequestParam("btitle") String btitle,
//				@RequestParam("bcontent") String bcontent,
//				@RequestParam(value = "bimg", required = false) MultipartFile bimg, Model model) {
//
//			try {
//				// 게시글 생성 (서비스 계층에서 처리)
//				AdminInfo admin = (AdminInfo) session.getAttribute("AdminUser");
//				Board newBoard = boardService.createBoard(btitle, bcontent, bimg, admin);
//
//				// 게시글이 저장되면 해당 게시글 정보 모델에 추가
//				model.addAttribute("board", newBoard);
//
//				// 성공적으로 저장된 후, 게시글 페이지로 리다이렉션
//				return "redirect:/board_detail/" + newBoard.getBuid(); // 예: 게시글 상세 페이지로 이동
//			} catch (Exception e) {
//				// 예외가 발생하면 오류 메시지를 모델에 추가
//				model.addAttribute("errorMessage", "게시글 작성에 실패했습니다: " + e.getMessage());
//				return "error"; // 오류 페이지로 이동
//			}
//		}
//
//	
//		
//	
//}
