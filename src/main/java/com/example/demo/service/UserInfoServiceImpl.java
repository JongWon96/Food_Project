package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.UserInfo;
import com.example.demo.persistence.UserInfoRepository;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoRepository userinfoRepo;

	// 회원 로그인
	// 리턴값: 1 - ID, Pw 일치, 0: Pw 불일치, -1: ID가 존재하지 않음.
	@Override
	public int loginUid(UserInfo vo) {
		int result = -1;
		
		UserInfo userinfo = userinfoRepo.findByUid(vo.getUid());
		
		if(userinfo == null) {
			result = -1;
		} else if(vo.getUpw().equals(userinfo.getUpw())) {
			result = 1;	// id, pw가 모두 일치	
		} else {
			result = 0; // 비밀번호 불일치			
		}
		
		return result;
	}

	// 회원 ID 확인
	// id가 존재하면 1, 존재하지 않으면 -1
	@Override
	public int confirmUid(String uid) {
		UserInfo userinfo = userinfoRepo.findByUid(uid);
		
		if(userinfo == null) {
			return -1;
		} else {
			return 1;
		}
	}

	// 회원정보 상세 조회
	@Override
	public UserInfo getUserInfo(String uid) {
		return userinfoRepo.findByUid(uid);
	}

	@Override
	public void insertUserInfo(UserInfo vo) {
		userinfoRepo.save(vo);

	}

	@Override
	public UserInfo getUidByUnameAndUphone(String uname, String uphone) {
		return userinfoRepo.findByUnameAndUphone(uname, uphone);
	}

	@Override
	public UserInfo getUpwByUidUnameUphone(String uid, String uname, String uphone) {
		return userinfoRepo.findByUidAndUnameAndUphone(uid, uname, uphone);
	}

	@Override
	public void changePassword(UserInfo vo) {
		userinfoRepo.changePassword(vo.getUid(), vo.getUpw());
	}

	@Override
	public List<UserInfo> getUserInfoList(String uname) {
		
		return userinfoRepo.getUserInfoList(uname);
	}

}
