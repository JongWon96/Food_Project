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



    // 사용자 ID와 비밀번호로 사용자 정보 찾기
    UserInfo findByUidAndUpw(String uid, String upw);


}