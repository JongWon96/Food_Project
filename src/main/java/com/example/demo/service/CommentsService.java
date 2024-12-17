package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Board;
import com.example.demo.domain.Comments;
import com.example.demo.domain.UserInfo;

public interface CommentsService {

	//댓글 저장
	public Comments saveComment(String ccontent,Board board,UserInfo author);
		
	public List<Comments> getCommentsByBoard(Board board);
	  	
	public Comments getCommentByCuid(int cuid);
	
	public Comments updateComment(int cuid , String ccontent);
	
	public void deleteComment(int cuid); 

}
