package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.User_Info;

public interface MemberRepository extends JpaRepository<User_Info, Integer> {

	public User_Info findByIdAndName(String U_Id, String U_Name);
		
}