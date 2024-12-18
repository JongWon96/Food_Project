package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.AdminInfo;
import com.example.demo.domain.UserInfo;

public interface AdminInfoRepository extends JpaRepository<AdminInfo, Integer> {
	
	public AdminInfo findByAid(String Aid);

}
