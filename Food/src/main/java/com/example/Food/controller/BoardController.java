package com.example.Food.controller;

import java.util.List;

import com.example.Food.domain.AdminInfo;
import com.example.Food.domain.Board;
import com.example.Food.domain.Comments;
import com.example.Food.domain.UserInfo;
import com.example.Food.dto.UserVo;
import com.example.Food.persistence.BoardRepository;
import com.example.Food.service.BoardService;
import com.example.Food.service.CommentsService;
import com.example.Food.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpSession;
@Controller
public class BoardController {

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentsService commentsService;

	@GetMapping("/boards")
	public String listBoards(@RequestParam(required = false) String searchKeyword,
							 @RequestParam(required = false) String searchCondition, @RequestParam(defaultValue = "0") int page,
							 Model model, HttpSession session) {

		UserInfo user = (UserInfo) session.getAttribute("loginUser");

		if (user != null) {
			model.addAttribute("loginUser", user.getUname()); // 로그인된 사용자의 이름
		} else {
			model.addAttribute("loginUser", null); // 로그인되지 않은 경우
		}

		AdminInfo admin = (AdminInfo) session.getAttribute("admininfoUser");

		if (admin != null) {
			model.addAttribute("admininfoUser", admin.getAname()); // 로그인된 사용자의 이름
		} else {
			model.addAttribute("admininfoUser", null); // 로그인되지 않은 경우
		}


		Page<Board> boardPage = null;

		Board mostLikedBoard = boardService.getMostLikedBoard();
		//	Board mostViewedBoard = boardService.getMostViewedBoard();

		model.addAttribute("mostLikedBoard", mostLikedBoard);
		//	model.addAttribute("mostViewedBoard", mostViewedBoard);
		Pageable pageable = PageRequest.of(page, 10); // 한 페이지에 10개 게시글 표시


		// 검색 조건에 따라 게시글을 검색

		if (searchCondition == null || searchCondition.isEmpty()) {
			boardPage = boardService.getBoardByCreatAt(pageable);
		} else if ("title".equals(searchCondition)) {
			// 제목으로 검색
			boardPage = boardService.getBoardByTitle(searchKeyword, pageable);
		} else if ("author".equals(searchCondition)) {
			// 작성자로 검색
			boardPage = boardService.getUserInfoByName(searchKeyword, pageable);
		} else if ("titleauthor".equals(searchCondition)) {
			// 제목 + 작성자 검색
			boardPage = boardService.getBoardByTitleAndAuthor(searchKeyword, pageable);
		}

		model.addAttribute("boardPage", boardPage);
		return "board/list"; // 게시글 목록을 렌더링할 뷰 페이지

	}

	//로그인 상태확인 로그인 시 작성페이지로 이동 아닐시 로그인 페이지로
	@GetMapping("/board_insert_form")
	public String BoardWriteForm(HttpSession session, Model model,AdminInfo vo) {

		UserInfo user = (UserInfo) session.getAttribute("loginUser");
		AdminInfo admin = (AdminInfo) session.getAttribute("admininfoUser");


		UserVo userVo = new UserVo();
		if (user == null) {
			model.addAttribute("msg", "로그인 후 이용해주세요.");
			model.addAttribute("redirectTo", "/login");
			return "board/alert";
		} else {
			model.addAttribute("userVo", userVo);
			return "board/boardInsert";
		}


}


	// 게시글 작성하기
	@PostMapping("/createBoard")
	public String createBoard(HttpSession session, @RequestParam("btitle") String btitle,
							  @RequestParam("bcontent") String bcontent,
							  @RequestParam(value = "bimg", required = false) MultipartFile bimg, Model model) {


		try {
			// 게시글 생성 (서비스 계층에서 처리)
			UserInfo user = (UserInfo) session.getAttribute("loginUser");
			Board newBoard = boardService.createBoard(btitle, bcontent, bimg, user);

			// 게시글이 저장되면 해당 게시글 정보 모델에 추가
			model.addAttribute("board", newBoard);

			// 성공적으로 저장된 후, 게시글 페이지로 리다이렉션
			return "redirect:/board_detail/" + newBoard.getBuid(); // 예: 게시글 상세 페이지로 이동
		} catch (Exception e) {
			// 예외가 발생하면 오류 메시지를 모델에 추가
			model.addAttribute("errorMessage", "게시글 작성에 실패했습니다: " + e.getMessage());
			return "error"; // 오류 페이지로 이동
		}
	}

	//게시글 상세보기
	@GetMapping("/board_detail/{buid}")
	public String getBoardDetail(@PathVariable("buid") Integer buid, Model model, HttpSession session) {
		// 게시글 조회
		Board board = boardService.getBoardByBuid(buid); // 게시글을 bUid로 조회

		if (board == null) {
			model.addAttribute("errorMessage", "해당 게시글을 찾을 수 없습니다.");
			return "board/errorPage";
		}

		// 로그인 상태일 때만 조회수 1 증가 중복증가 x
		UserInfo user = (UserInfo) session.getAttribute("loginUser");

		if (user != null) {
			model.addAttribute("loginUser", user.getUname());
		} else {
			model.addAttribute("loginUser", null);
		}


		AdminInfo admin = (AdminInfo) session.getAttribute("admininfoUser");

		if (admin != null) {
			model.addAttribute("admininfoUser", admin.getAname()); // 로그인된 사용자의 이름
		} else {
			model.addAttribute("admininfoUser", null); // 로그인되지 않은 경우
		}

		// 로그인한 사용자일 때만 조회수 증가 처리
		if (session.getAttribute("viewedBoard" + buid) == null) {
			boardService.incrementBoardViews(buid);
			session.setAttribute("viewedBoard" + buid, true); // 세션에 해당 게시글을 본 기록 추가
		}

		// 이미지 경로를 모델에 추가
		String imagePath = board.getBimg(); // 게시글 이미지 경로 가져오기
		// 댓글 목록 조회
		List<Comments> comments = commentsService.getCommentsByBoard(board); // 게시글에 해당하는 댓글 조회

		// 모델에 게시글과 댓글 이미지 추가
		model.addAttribute("board", board);
		model.addAttribute("comments", comments);
		model.addAttribute("imagePath", imagePath);
		return "board/boardDetail"; // 상세보기 페이지 반환

	}

	// 게시글 상세페이지에서 게시글 삭제 및 수정-------------------------------------------------
	//게시글 수정
	@GetMapping("/board_edit_form/{buid}")
	public String getBoardEditForm(@PathVariable("buid") int buid, Model model, HttpSession session) {
		// 로그인한 사용자 정보 가져오기
		UserInfo user = (UserInfo) session.getAttribute("loginUser");

		if (user == null) {
			model.addAttribute("msg", "로그인 후 수정 가능합니다.");
			model.addAttribute("redirectTo", "/login");
			return "board/alert";
		}

		AdminInfo admin = (AdminInfo) session.getAttribute("admininfoUser");

		if (admin != null) {
			model.addAttribute("admininfoUser", admin.getAname()); // 로그인된 사용자의 이름
		} else {
			model.addAttribute("admininfoUser", null); // 로그인되지 않은 경우
		}


		// 게시글 조회
		Board board = boardService.getBoardByBuid(buid);

		if (board == null) {
			model.addAttribute("errorMessage", "해당 게시글을 찾을 수 없습니다.");
			return "board/errorPage";
		}

		// 게시글 작성자와 로그인한 사용자가 동일한지 확인
		if (board.getAuthor().getUuid() != user.getUuid()) {
			model.addAttribute("msg", "해당 게시글의 권한이 없습니다.");
			model.addAttribute("redirectTo", "/board_detail/" + buid); // 게시글 상세 페이지로 리디렉션
			return "board/alert";
		}

		// 게시글 수정 폼으로 이동
		model.addAttribute("board", board);
		return "board/boardEdit";
	}

	// 게시글 수정 처리
	@PostMapping("/board_edit/{buid}")
	public String editBoard(@PathVariable("buid") int buid, @RequestParam("btitle") String btitle,
							@RequestParam("bcontent") String bcontent,
							@RequestParam(value = "bimg", required = false) MultipartFile bimg, Model model, HttpSession session) {

		try {
			// 게시글 수정 처리
			boardService.updateBoard(buid, btitle, bcontent, bimg);
			return "redirect:/board_detail/" + buid; // 수정 후 해당 게시글 상세 페이지로 리다이렉트
		} catch (Exception e) {
			model.addAttribute("errorMessage", "게시글 수정에 실패했습니다: " + e.getMessage());
			return "board/errorPage"; // 오류 페이지
		}

	}

	// 게시물 삭제
	@GetMapping("/board_delete/{buid}")
	public String deleteBoard(@PathVariable("buid") int buid, HttpSession session, Model model,
							  RedirectAttributes redirectAttributes) {
		UserInfo user = (UserInfo) session.getAttribute("loginUser");

		if (user == null) {
			model.addAttribute("msg", "로그인 후 게시글 삭제가 가능합니다.");
			model.addAttribute("redirectTo", "/login");
			return "board/alert";
		}

		AdminInfo admin = (AdminInfo) session.getAttribute("admininfoUser");

		if (admin == null) {
			model.addAttribute("msg", "로그인 후 게시글 삭제가 가능합니다.");
			model.addAttribute("redirectTo", "/login");
			return "board/alert";
		}


		Board board = boardService.getBoardByBuid(buid);

		if (board.getAuthor() == null || board.getAuthor().getUuid() != user.getUuid()) {
			model.addAttribute("msg", "해당 게시글의 권한이 없습니다.");
			model.addAttribute("redirectTo", "/board_detail/" + buid); // 게시글 상세 페이지로 리디렉션
			return "board/alert";
		}

		try {
			boardService.deleteBoard(buid); // 서비스 계층에서 게시글 삭제
			redirectAttributes.addFlashAttribute("msg", "게시글이 성공적으로 삭제되었습니다.");
			return "redirect:/boards";
		} catch (Exception e) {
			model.addAttribute("errorMessage", "게시글 삭제에 실패했습니다: " + e.getMessage());
			return "board/errorPage"; // 오류 페이지
		}
	}
	// 게시글 신고 처리
	@PostMapping("/board_danger/{buid}")
	public String reportBoard(@PathVariable("buid") int buid,Model model) {
		try {
			// 신고 처리 서비스 호출 (신고 이유는 더 이상 처리하지 않음)
			boardService.DangerBoard(buid);

			return "redirect:/board_detail/" + buid; // 게시글 상세 페이지로 리다이렉트
		} catch (Exception e) {
			model.addAttribute("errorMessage", "신고에 실패하였습니다." + e.getMessage());
			return "board/errorPage";
		}
	}

	@PostMapping("/board_like/{buid}")
	public String likeBoard(@PathVariable("buid") int buid, HttpSession session, Model model) {
		// 로그인한 사용자 정보를 세션에서 가져옵니다.
		UserInfo user = (UserInfo) session.getAttribute("loginUser");

		if (user == null) {
			model.addAttribute("msg", "로그인 후 좋아요를 누를 수 있습니다.");
			model.addAttribute("redirectTo", "/login");
			return "board/alert";
		}


		// 이미 해당 게시글에 대해 좋아요를 눌렀는지 체크 (세션을 활용)
		if (session.getAttribute("likedBoard" + buid) != null) {
			model.addAttribute("msg", "이미 이 게시글에 좋아요를 눌렀습니다.");
			model.addAttribute("redirectTo", "/board_detail/" + buid);
			return "board/alert"; // 이미 좋아요를 눌렀다면 알림 페이지로
		}

		try {
			// 게시글 좋아요 처리 (서비스 계층에서)
			boardService.incrementBoardLikes(buid);

			// 세션에 좋아요 기록을 추가 (이 게시글에 대해 좋아요를 눌렀다고 기록)
			session.setAttribute("likedBoard" + buid, true);

			// 좋아요가 추가된 후 해당 게시글의 상세 페이지로 리다이렉트
			return "redirect:/board_detail/" + buid;

		} catch (Exception e) {
			model.addAttribute("errorMessage", "좋아요 처리에 실패했습니다: " + e.getMessage());
			return "board/errorPage";
		}
	}



	////////댓글 작성 수정 삭제  ------------------------------------------------------------------
//작성
	@PostMapping("/comment/write")
	public String saveComment(@RequestParam("ccontent") String ccontent, @RequestParam("buid") Integer buid,
							  HttpSession session, Model model) {
		// 세션에서 로그인한 사용자 정보 가져오기
		UserInfo user = (UserInfo) session.getAttribute("loginUser");
		AdminInfo admin = (AdminInfo) session.getAttribute("admininfoUser");

		// 로그인 상태 확인
		if (user == null) {
			model.addAttribute("msg", "로그인 상태가 아닙니다.");
			return "redirect:/login";
		}

		// 게시글 조회
		Board board = boardService.getBoardByBuid(buid);

		// 게시글 존재 여부 확인
		if (board == null) {
			model.addAttribute("msg", "해당 게시글을 찾을 수 없습니다.");
			return "redirect:/boards"; // 게시판 목록으로 리다이렉트
		}

		// 댓글 내용이 비어 있는 경우 예외 처리
		if (ccontent == null || ccontent.trim().isEmpty()) {
			model.addAttribute("msg", "댓글 내용을 입력해주세요.");
			model.addAttribute("redirectTo", "/board_detail/" + buid);
			return "redirect:/board_detail/" + buid; // 게시글 상세 페이지로 리다이렉트
		}

		// 댓글 저장
		Comments newComment = commentsService.saveComment(ccontent, board, user);

		// 댓글이 달린 게시글로 리다이렉트
		return "redirect:/board_detail/" + buid;
	}
	@PostMapping("/comment_edit/{cuid}")
	public String editComment(@PathVariable("cuid") int cuid,
							  @RequestParam("ccontent") String ccontent,
							  Model model,
							  HttpSession session) {
		// 로그인한 사용자 정보 가져오기
		UserInfo user = (UserInfo) session.getAttribute("loginUser");

		// 로그인 상태 확인
		if (user == null) {
			model.addAttribute("msg", "로그인 후 수정할 수 있습니다.");
			return "redirect:/user_login_form"; // 로그인 페이지로 리디렉션
		}

		// 댓글 조회
		Comments comment = commentsService.getCommentByCuid(cuid);

		if (comment == null) {
			model.addAttribute("errorMessage", "댓글을 찾을 수 없습니다.");
			return "board/errorPage"; // 오류 페이지
		}

		// 댓글 작성자와 로그인한 사용자가 동일한지 확인
		if (comment.getAuthor().getUuid() != user.getUuid()) {
			model.addAttribute("msg", "자신의 댓글만 수정할 수 있습니다.");
			model.addAttribute("redirectTo", "/board_detail/" + comment.getBoard().getBuid());
			return "board/alert"; // 알림 페이지
		}
		System.out.println("cuid"+cuid);
		try {
			// 댓글 수정 처리
			commentsService.updateComment(cuid, ccontent); // 댓글 수정 서비스 호출

			// 수정된 댓글이 달린 게시글 상세 페이지로 리디렉션
			return "redirect:/board_detail/" + comment.getBoard().getBuid();
		} catch (Exception e) {
			model.addAttribute("errorMessage", "댓글 수정에 실패했습니다: " + e.getMessage());
			return "board/errorPage"; // 오류 페이지
		}
	}
	@PostMapping("/comment_delete/{cuid}")
	public String deleteComment(@PathVariable("cuid") int cuid, Model model, HttpSession session) {
		// 로그인한 사용자 정보 가져오기
		UserInfo user = (UserInfo) session.getAttribute("loginUser");

		// 로그인 상태 확인
		if (user == null) {
			model.addAttribute("msg", "로그인 후 삭제할 수 있습니다.");
			return "redirect:/user_login_form"; // 로그인 페이지로 리디렉션
		}

		// 댓글 조회
		Comments comment = commentsService.getCommentByCuid(cuid);

		if (comment == null) {
			model.addAttribute("errorMessage", "댓글을 찾을 수 없습니다.");
			return "board/errorPage"; // 오류 페이지
		}

		// 댓글 작성자와 로그인한 사용자가 동일한지 확인
		if (comment.getAuthor().getUuid() != user.getUuid()) {
			model.addAttribute("msg", "자신의 댓글만 삭제할 수 있습니다.");
			model.addAttribute("redirectTo", "/board_detail/" + comment.getBoard().getBuid());
			return "board/alert"; // 알림 페이지
		}

		int boardBuid = comment.getBoard().getBuid();
		// 댓글 삭제 처리
		commentsService.deleteComment(cuid); // 댓글 삭제 서비스 호출

		return "redirect:/board_detail/" + boardBuid; // 댓글이 삭제된 게시글 상세 페이지로 리디렉션
	}

	// 로그아웃 요청 처리
	@PostMapping("board/logout")
	public String logout(HttpSession session) {
		// 세션에서 로그인된 사용자 정보 삭제
		session.invalidate();
		// 로그아웃 후 게시판 목록 페이지로 리다이렉트
		return "redirect:/boards";
	}

}



