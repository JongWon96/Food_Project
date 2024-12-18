package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.AdminInfo;
import com.example.demo.persistence.AdminInfoRepository;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {
	@Autowired
	private AdminInfoRepository admininfoRepo;
	
	/*
	 * 관리자 로그인: adminCheck()
	 * 리턴값: -1 - id가 존재하지 않음
	 *        0 - 비밀번호 맞지 않음
	 *        1 - 정상 로그인  
	 */
	@Override
	public int adminCheck(AdminInfo vo) {
		int result = -1;  // 로그인 결과 저장
		
		// admin 테이블에서 관리자 정보 조회
		AdminInfo admininfo = admininfoRepo.findByAid(vo.getAid());
		
		if(admininfo == null) {
			result = -1;			
		} else if(vo.getApw().equals(admininfo.getApw())) {
			result = 1;
		} else {
			result = 0;
		}
		
		return result;
	}
	
	/*
	 * 관리자 정보 조회	
	 */
	@Override
	public AdminInfo getAdminInfo(String aid) {
		return admininfoRepo.findByAid(aid);
	}

}
