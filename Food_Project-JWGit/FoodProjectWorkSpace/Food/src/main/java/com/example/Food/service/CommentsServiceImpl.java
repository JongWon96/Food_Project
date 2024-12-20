package com.example.Food.service;

import java.util.List;

import com.example.Food.domain.Board;
import com.example.Food.domain.Comments;
import com.example.Food.domain.UserInfo;
import com.example.Food.persistence.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentsServiceImpl implements CommentsService {

	    @Autowired
	    private CommentsRepository commentsRepository;
	    
	    

	    public Comments saveComment(String ccontent, Board board, UserInfo author ){
	    	Comments newcomment = new Comments();
	    	newcomment.setCcontent(ccontent);
	    	newcomment.setBoard(board);    
	    	newcomment.setAuthor(author);
	    return commentsRepository.save(newcomment);
	
	    }

//	    public List<Comments> getCommentsByauthor(UserInfo author) {
//	    	  return commentsRepository.findByAuthor(author);
//	    }
//
		@Override
		public List<Comments> getCommentsByBoard(Board board) {
		    return commentsRepository.findByBoard(board);  // Board 객체를 기준으로 댓글을 조회

		}

		@Override
		   public Comments getCommentByCuid(int cuid) {
	        return commentsRepository.findById(cuid)
	                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
	    }

		@Override
		public Comments updateComment(int cuid, String ccontent) {
		
			Comments comment = commentsRepository.findById(cuid)
			         .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
			comment.setCcontent(ccontent);
			
			return commentsRepository.save(comment);
			
		}

		@Override
		public void deleteComment(int cuid) {
			
			Comments comment = commentsRepository.findById(cuid)
			         .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));	
			
			commentsRepository.delete(comment);
		}


}

