package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.domain.Board;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    //등록일 순으로 내림차순 정렬
    @Query("SELECT b FROM Board b ORDER BY b.bcreateAt DESC")
    Page<Board> findAllByOrderByBcreatedAtDesc(Pageable pageable);
    
	
    // 게시글 조회수 증가
    @Modifying
    @Transactional
    @Query("UPDATE Board b SET b.bcnt = b.bcnt + 1 WHERE b.buid = :buid")
    void incrementViews(int buid);
    
    Board findTopByOrderByBcntDesc();
    // 제목으로 검색 
    Page<Board> findByBtitleContaining(String btitle, Pageable pageable);

    // 작성자 이름으로 검색
    Page<Board> findByAuthorUnameContaining(String uname, Pageable pageable);
    //제목+작성자로 검색
    Page<Board> findByBtitleContainingOrAuthorUnameContaining(String btitle,String uname,Pageable pageable);
    
    //UID에 맞는 해당 게시물 반환
    Optional<Board> findByBuid(Integer buid);

    // 특정 게시글에 신고 처리 
    @Modifying
    @Transactional
    @Query("UPDATE Board b SET b.bdanger = 1 WHERE b.buid = :buid")
    void reportBoard(int buid);
    
    
    List<Board> findByBdanger(int bdanger);
}
