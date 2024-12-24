package com.example.Food.service;

import com.example.Food.domain.Board;
import com.example.Food.domain.Comments;
import com.example.Food.domain.UserInfo;

import java.util.List;



public interface CommentsService {

	//댓글 저장
	public Comments saveComment(String ccontent, Board board, UserInfo author);
		
	public List<Comments> getCommentsByBoard(Board board);
	  	
	public Comments getCommentByCuid(int cuid);
	
	public Comments updateComment(int cuid , String ccontent);
	
	public void deleteComment(int cuid); 

}
