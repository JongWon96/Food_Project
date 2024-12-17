package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.domain.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {

   
     // 작성일 기준으로 게시글을 조회합니다.
     
    public Page<Board> getBoardByCreatAt(Pageable pageable);
     
    //제목을 포함하는 게시글을 조회합니다.
 
    public Page<Board> getBoardByTitle(String searchKeyword, Pageable pageable);

    
     //작성자 이름을 포함하는 게시글을 조회합니다.
     
    public Page<Board> getUserInfoByName(String searchKeyword, Pageable pageable);

    
    // 제목 또는 작성자로 게시글을 조회합니다.
     
    public Page<Board> getBoardByTitleAndAuthor(String searchKeyword, Pageable pageable);

  
    // 특정 게시글을 ID로 조회합니다.
     
    public Board getBoardByBuid(Integer buid);

    
     //게시글의 조회수를 증가시킵니다.
     
    void incrementBoardViews(int buid);
   
     // 새로운 게시글을 생성합니다.
     
    public Board createBoard(String btitle, String bcontent, MultipartFile bimg, UserInfo author) throws Exception;

    
     // 기존 게시글을 수정합니다.
     
    public Board updateBoard(int buid, String btitle, String bcontent, MultipartFile bimg) throws Exception;

    /**
     * 게시글을 삭제합니다.
     */
    void deleteBoard(int buid);

    /**
     * 이미지를 저장합니다.
     */
    public String saveImage(MultipartFile bimg) throws IOException;

    /**
     * 게시글을 신고 처리합니다(신고 1처리)
     */
    void DangerBoard(int buid);

    /**
     * 신고 개시글 이상무 (신고 0 처리)
     */
    public void approveBoard(int buid);
    
    /**
     * 조회수가 가장 높은 게시글을 조회합니다.
     */
    public Board getMostViewedBoard();

    /**
     * 신고된 게시글 목록을 조회합니다.
     */
    public List<Board> getDangerBoards();

    /**
     * 이미지를 포함하여 게시글을 수정합니다.
     */
    public Board updateBoardWithImage(int buid, String title, String bcontent, MultipartFile bimg) throws IOException;

    /**
     * 제목과 내용만 수정하여 게시글을 업데이트합니다.
     */
    public Board updateBoard(int buid, String title, String bcontent);


}
