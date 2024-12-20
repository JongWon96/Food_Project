package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.UserInfo;
import com.example.demo.dto.UserBmrDto;

import jakarta.transaction.Transactional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
   
   public UserInfo findByUid(String uid);
   
   // Name과 Phone으로 id 조회
   public UserInfo findByUnameAndUphone(String uname, String uphone);
   
   // Id와 Name과 Phone으로 pw 조회
   public UserInfo findByUidAndUnameAndUphone(String uid, String uname, String uphone);
   
   @Transactional
   @Modifying
   @Query(value="UPDATE User_Info SET U_Pw=:upw WHERE U_Id=:uid", nativeQuery=true)
   public void changePassword(@Param("uid") String uid, @Param("upw") String upw);

   // 회원명을 조건으로 회원목록 조회
   public List<UserInfo> findUserInfoByUnameContaining(String uname);
   
   @Query(value="SELECT * FROM User_Info WHERE U_Name LIKE %:uname%", nativeQuery=true)
   public List<UserInfo> getUserInfoList(@Param("uname") String uname);
   
	// 사용자별 기초대사량 조회
   @Query("SELECT new com.example.demo.dto.UserBmrDto(u.uname, u.uage, u.uweight, u.uheight, u.ugender, " +
	       "(CASE WHEN u.ugender = 1 THEN (66.47 + (13.75 * u.uweight) + (5 * u.uheight) - (6.76 * u.uage)) " +
	       "ELSE (655.1 + (9.563 * u.uweight) + (1.850 * u.uheight) - (4.676 * u.uage)) END)) " +
	       "FROM UserInfo u WHERE u.uuid = :userUid")
	UserBmrDto findUserInfoByUid(@Param("userUid") int userUid);
}