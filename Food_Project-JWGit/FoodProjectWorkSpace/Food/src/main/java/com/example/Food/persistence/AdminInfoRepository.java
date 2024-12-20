package com.example.Food.persistence;

import com.example.Food.domain.AdminInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminInfoRepository extends JpaRepository<AdminInfo, Integer> {
	
	public AdminInfo findByAid(String aid);



}
