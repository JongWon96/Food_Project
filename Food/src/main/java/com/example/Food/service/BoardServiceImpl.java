package com.example.Food.service;


import com.example.Food.domain.Board;
import com.example.Food.domain.UserInfo;
import com.example.Food.persistence.BoardRepository;
import com.example.Food.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userInfoRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;  // 파일을 저장할 디렉토리 경로


    // 조회수 젤 높은 게시글
    @Override
    public Board getMostViewedBoard() {
        return boardRepository.findTopByOrderByBcntDesc();
    }

    // 좋아요 젤 높은 게시글
    @Override
    public Board getMostLikedBoard() {
        return boardRepository.findTopByOrderByBlikecntDesc();
    }


    @Override
    public Page<Board> getBoardByCreatAt(Pageable pageable) {
        return boardRepository.findAllByOrderByBcreatedAtDesc(pageable);
    }

    @Override
    public Page<Board> getBoardByTitle(String searchKeyword, Pageable pageable) {
        return boardRepository.findByBtitleContaining(searchKeyword, pageable);
    }

    // 작성자 이름으로 게시글 조회
    @Override
    public Page<Board> getUserInfoByName(String searchKeyword, Pageable pageable) {
        return boardRepository.findByAuthorUnameContaining(searchKeyword, pageable);
    }

    // 제목 + 작성자로 게시글 조회
    @Override
    public Page<Board> getBoardByTitleAndAuthor(String searchKeyword, Pageable pageable) {
        return boardRepository.findByBtitleContainingOrAuthorUnameContaining(searchKeyword, searchKeyword, pageable);
    }

    @Override
    public Board getBoardByBuid(Integer buid) {
        return boardRepository.findByBuid(buid)
                .orElseThrow(() -> new RuntimeException("해당 게시글을 찾을 수 없습니다."));
    }

    @Override
    public void incrementBoardViews(int buid) {
        boardRepository.incrementViews(buid);  // 조회수 증가
    }

    // 게시글 생성
    @Override
    public Board createBoard(String btitle, String bcontent, MultipartFile bimg, UserInfo author) throws Exception {
        // 제목과 내용 필수처리
        if (btitle == null || btitle.trim().isEmpty() || bcontent == null || bcontent.trim().isEmpty()) {
            throw new IllegalArgumentException("제목과 내용은 필수 입력 항목입니다.");
        }

        // 새 게시글 객체 생성
        Board newBoard = new Board();
        newBoard.setBtitle(btitle);
        newBoard.setBcontent(bcontent);

        // 이미지 처리
        if (bimg != null && !bimg.isEmpty()) {
            String imagePath = saveImage(bimg);

            newBoard.setBimg(imagePath);  // 이미지 경로 설정
        }

        newBoard.setAuthor(author);
        newBoard.setBcnt(0);  // 조회수 초기화

        return boardRepository.save(newBoard);  // 게시글 저장
    }
    //이미지 수정
    @Override
    public String saveImage(MultipartFile bimg) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + bimg.getOriginalFilename();
        Path path = Paths.get(uploadDir, fileName);
        Files.copy(bimg.getInputStream(), path);  // 파일 저장
        return "/uploads/" + fileName;  // 웹에서 참조할 수 있는 경로 반환
    }

    // 게시글 수정
    @Override
    public Board updateBoard(int buid, String title, String bcontent, MultipartFile bimg) throws IOException {
        Board board = boardRepository.findByBuid(buid)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        board.setBtitle(title);
        board.setBcontent(bcontent);

        // 이미지 수정 처리
        if (bimg != null && !bimg.isEmpty()) {
            String imagePath = saveImage(bimg);  // 새 이미지 저장
            board.setBimg(imagePath);  // 이미지 경로 업데이트
        }

        return boardRepository.save(board);  // 수정된 게시글 저장
    }

    // 게시글 삭제
    @Override
    public void deleteBoard(int buid) {
        Board board = boardRepository.findByBuid(buid)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(board);  // 게시글 삭제
    }

    // 게시글 신고 처리
    @Override
    public void DangerBoard(int buid) {
        Board board = boardRepository.findByBuid(buid)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        board.setBdanger(1);  // 신고 상태 업데이트
        boardRepository.save(board);  // 신고 상태 저장
    }

    // 신고된 게시글 목록 조회
    public List<Board> getDangerBoards() {
        return boardRepository.findByBdanger(1);  // bdanger가 1인 게시글 조회
    }

    @Override
    public Board updateBoardWithImage(int buid, String title, String bcontent, MultipartFile bimg) throws IOException {
        Board board = boardRepository.findByBuid(buid)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        board.setBtitle(title);
        board.setBcontent(bcontent);

        // 이미지가 있을 경우 이미지 저장 처리
        if (bimg != null && !bimg.isEmpty()) {
            String imagePath = saveImage(bimg);  // 새 이미지 저장
            board.setBimg(imagePath);  // 이미지 경로 업데이트
        }

        return boardRepository.save(board); // 수정된 게시글 저장
    }
    @Override
    public Board updateBoard(int buid, String title, String bcontent) {
        Board board = boardRepository.findByBuid(buid)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        board.setBtitle(title);
        board.setBcontent(bcontent);

        return boardRepository.save(board); // 수정된 게시글 저장

    }

    // 게시글 승인 처리 (신고 상태를 0으로 변경)
    public void approveBoard(int buid) {
        Board board = boardRepository.findByBuid(buid)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        board.setBdanger(0);
        boardRepository.save(board);
    }

    public void incrementBoardLikes(int buid) {
        // 게시글 조회
        Board board = boardRepository.findById(buid)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // 게시글의 좋아요 수 증가
        board.setBlikecnt(board.getBlikecnt() + 1);

        // 좋아요 수 업데이트
        boardRepository.save(board);
    }






}
