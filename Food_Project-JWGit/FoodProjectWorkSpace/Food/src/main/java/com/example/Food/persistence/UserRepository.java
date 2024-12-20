package com.example.Food.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Food.domain.UserInfo;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {


    static void setUweight(double uweight) {
    }

    static void setUheight(double uheight) {
    }

    static void setUallergy(int uallergy) {
    }

    static void setStyle(String style) {
    }

    // 사용자 ID와 비밀번호로 사용자 정보 찾기
    UserInfo findByUidAndUpw(String uid, String upw);

    // 비밀번호 변경
    @Transactional
    @Modifying
    @Query(value = "UPDATE User_Info SET U_Pw=:upw WHERE U_Id=:uid", nativeQuery = true)
    void changePassword(@Param("uid") String uid, @Param("upw") String upw);

    // 사용자 이름으로 검색 (부분 일치)
    List<UserInfo> findUserInfoByUnameContaining(String uname);


    @Query(value="select * from user_info WHERE u_name LIKE %:uname%", nativeQuery=true)
    public List<UserInfo> getUserInfoList(@Param("uname") String uname);

    @Query(value="select * from user_info WHERE u_id=:uid", nativeQuery=true)
    public UserInfo findByUid(@Param("uid") String uid);

    @Query(value="select * from user_info WHERE u_name=:uname AND u_phone=:uphone", nativeQuery=true)
    public UserInfo findByUnameAndUphone(@Param("uname") String uname, @Param("uphone") String uphone);

    @Query(value="select * from user_info WHERE u_id=:uid AND u_name=:uname AND u_phone=:uphone", nativeQuery=true)
    public UserInfo findByUidAndUnameAndUphone(@Param("uid") String uid, @Param("uname") String uname, @Param("uphone") String uphone);



}