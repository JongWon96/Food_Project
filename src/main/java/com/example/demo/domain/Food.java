package com.example.demo.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Food")
@Getter
@Setter
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "F_UID")
	private Long fUid;

	@Column(name = "F_name", nullable = false)
	private String fName;

	@Column(name = "F_img")
	private String fImg; // 음식 이미지 URL

	@Column(name = "F_Category")
	private String fCategory; // 음식 카테고리

	// 연관관계 매핑
	@OneToOne(mappedBy = "food", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private FoodDetail foodDetail;
	
	public Food() {
		super();
	}
}