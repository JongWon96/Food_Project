	package com.example.demo.service;
	
	import com.example.demo.domain.UserInfo;
	import com.example.demo.persistence.UserInfoRepository;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	
	import java.util.Optional;
	
	@Service
	public class UserInfoServiceImpl implements UserInfoService {
	
	    @Autowired
	    private UserInfoRepository userInfoRepository;
	
	    @Override
	    public UserInfo getUserInfoByUuId(int uuid) {
	        return userInfoRepository.findById(uuid)
	                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
	    }
	
	    @Override
	    public UserInfo createUserInfo(UserInfo userInfo) {
	        return userInfoRepository.save(userInfo);
	    }
	
	    @Override
		public UserInfo getUserInfoByUId(String uid) {
		return userInfoRepository.findByUid(uid) .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
		
		}
	    @Override
	    public UserInfo updateUserInfo(int uuid, UserInfo userInfo) {
	        UserInfo existingUser = userInfoRepository.findById(uuid)
	                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
	        existingUser.setUid(userInfo.getUid());
	        existingUser.setUpw(userInfo.getUpw());
	        existingUser.setUname(userInfo.getUname());
	        existingUser.setUphone(userInfo.getUphone());
	        existingUser.setUgender(userInfo.getUgender());
	        existingUser.setUage(userInfo.getUage());
	        existingUser.setUweight(userInfo.getUweight());
	        existingUser.setUheight(userInfo.getUheight());
	        existingUser.setUgoal(userInfo.getUgoal());
	        existingUser.setUallergy(userInfo.getUallergy());
	        
	        return userInfoRepository.save(existingUser);
	    }
	
	    @Override
	    public void deleteUserInfo(int uuid) {
	        UserInfo userInfo = userInfoRepository.findById(uuid)
	                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
	        userInfoRepository.delete(userInfo);
	    }

		
	}
