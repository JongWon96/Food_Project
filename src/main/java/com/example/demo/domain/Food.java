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

@Entity
@Table(name = "Food")
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

	public Long getfUid() {
		return fUid;
	}

	public void setfUid(Long fUid) {
		this.fUid = fUid;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public FoodDetail getFoodDetail() {
		return foodDetail;
	}

	public void setFoodDetail(FoodDetail foodDetail) {
		this.foodDetail = foodDetail;
	}

	public String getfImg() {
		return fImg;
	}

	public void setfImg(String fImg) {
		this.fImg = fImg;
	}

	public String getfCategory() {
		return fCategory;
	}

	public void setfCategory(String fCategory) {
		this.fCategory = fCategory;
	}
}