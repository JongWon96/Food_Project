package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Board;
import com.example.demo.domain.Comments;
import com.example.demo.domain.UserInfo;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {




   List<Comments> findByBoard(Board board);
    
    
}
