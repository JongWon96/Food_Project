package com.example.Food.persistence;

import java.util.List;

import com.example.Food.domain.Board;
import com.example.Food.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {




   List<Comments> findByBoard(Board board);
    
    
}
