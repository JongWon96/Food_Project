package com.example.demo.persistence;
import com.example.demo.domain.UserInfo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	  Optional<UserInfo> findByUid(String uid);
}
