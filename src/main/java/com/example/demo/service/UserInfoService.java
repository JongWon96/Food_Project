package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.UserInfo;

public interface UserInfoService {
	// 회원 로그인
		// 리턴값: 1 - ID, 0: Pwd 불일치, -1: ID가 존재하지 않음. 
		public int loginUid(UserInfo vo);
		
		// 회원 ID 확인
		// id가 존재하면 1, 존재하지 않으면 -1
		public int confirmUid(String uid);
		
		// 회원정보 상세 조회
		public UserInfo getUserInfo(String uid);
		
		// 회원정보 저장
		public void insertUserInfo(UserInfo vo);

		// Name과 Phone으로 id 찾기
		public UserInfo getUidByUnameAndUphone(String uname, String uphone);
		
		// Id와 Name과 Phone으로 pw 찾기
		public UserInfo getUpwByUidUnameUphone(String uid, String uname, String uphone);
		
		public void changePassword(UserInfo vo);
		
		// 전체 회원 조회
		public List<UserInfo> getUserInfoList(String uname);
		

}
