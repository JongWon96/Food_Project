package com.example.demo.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "FoodDiary")
public class FoodDiary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 ID
	@Column(name = "D_UID")
	private Long d_Uid;

	@Column(name = "D_Date")
	private Date d_date;

	@Column(name = "D_Meal")
	private Number d_meal;

	// 연관관계 매핑
	@OneToOne
	@JoinColumn(name = "F_UID", nullable = false)
	private Food food;

	@ManyToOne
	@JoinColumn(name = "U_UID", nullable = false)
	private UserInfo userInfo;

	public FoodDiary() {
		super();
	}

	public Long getD_Uid() {
		return d_Uid;
	}

	public void setD_Uid(Long d_Uid) {
		this.d_Uid = d_Uid;
	}

	public Date getD_date() {
		return d_date;
	}

	public void setD_date(Date d_date) {
		this.d_date = d_date;
	}

	public Number getD_meal() {
		return d_meal;
	}

	public void setD_meal(Number d_meal) {
		this.d_meal = d_meal;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
}
