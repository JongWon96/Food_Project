package com.example.Food.domain;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor	// 기본생성자 생성
@AllArgsConstructor	// 모든 멤버를 매개변수로 하는 생성자
@DynamicInsert  	// 필요한 값만 insert (deafault 값 사용시)
@DynamicUpdate		// 필요한 값만 update
@Entity

public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "C_UID")
	private int cuid;		//댓글UID
	@Column(name = " C_content")
	private String ccontent;	//댓글 글내용
	@Column(name = "C_createAt")
	   @ColumnDefault("sysdate")
	private Date ccreateAt;	//댓글 작성일
	
    @ManyToOne   
    @JoinColumn(name = "B_UID")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "U_UID")
    private UserInfo author; 

	


}
