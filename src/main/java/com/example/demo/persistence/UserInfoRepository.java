package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.UserInfo;

import jakarta.transaction.Transactional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {	

	@Transactional  // 커밋, 롤백 처리
	@Modifying  // @Query와 UPDATE 사용할때 사용하는 어노테이션임
	@Query(value="UPDATE user_info SET u_pw=:upw WHERE u_id=:uid", nativeQuery=true)	
	public void changePassword(@Param("uid") String uid, @Param("upw") String upw);

	// 회원명을 조건으로 회원목록 조회
	public List<UserInfo> findUserInfoByUnameContaining(String uname);
	
	@Query(value="select * from user_info WHERE u_name LIKE %:uname%", nativeQuery=true)
	public List<UserInfo> getUserInfoList(@Param("uname") String uname);
	
	@Query(value="select * from user_info WHERE u_id=:uid", nativeQuery=true)
	public UserInfo findByUid(@Param("uid") String uid);
	
	@Query(value="select * from user_info WHERE u_name=:uname AND u_phone=:uphone", nativeQuery=true)
	public UserInfo findByUnameAndUphone(@Param("uname") String uname, @Param("uphone") String uphone);
	
	@Query(value="select * from user_info WHERE u_id=:uid AND u_name=:uname AND u_phone=:uphone", nativeQuery=true)
	public UserInfo findByUidAndUnameAndUphone(@Param("uid") String uid, @Param("uname") String uname, @Param("uphone") String uphone);
}
