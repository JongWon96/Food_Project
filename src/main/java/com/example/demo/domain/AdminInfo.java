package com.example.demo.domain;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate  // 변경되는 컬럼만 수정할 수 있도록 함.
@Entity  // @Entity는 최종으로 입력해 준다.
public class AdminInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "A_Uid")
	private int auid;
	
	@Column(name = "A_Id")
	private String aid;
	
	@Column(name = "A_Pw")
	private String apw;
	
	@Column(name = "A_Name")
	private String aname;
	
	@Column(name = "A_Phone")
	private int aphone;
}
