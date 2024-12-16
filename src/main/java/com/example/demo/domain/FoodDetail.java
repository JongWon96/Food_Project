package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Food_Detail")
public class FoodDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FD_UID")
    private Long fdUid;

    @Column(name = "FD_kcal")
    private int kcal;

	@Column(name = "FD_carb")
	private int carb;

	@Column(name = "FD_prt")
	private int prt;

	@Column(name = "FD_fat")
	private int fat;

	@Column(name = "FD_sugar")
	private int sugar;

	@Column(name = "FD_allergy")
	private String allergy;

	@OneToOne
	@JoinColumn(name = "F_UID")
	private Food food;

	public FoodDetail() {
		super();
	}

	public Long getFdUid() {
		return fdUid;
	}

	public void setFdUid(Long fdUid) {
		this.fdUid = fdUid;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public int getCarb() {
		return carb;
	}

	public void setCarb(int carb) {
		this.carb = carb;
	}

	public int getPrt() {
		return prt;
	}

	public void setPrt(int prt) {
		this.prt = prt;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getSugar() {
		return sugar;
	}

	public void setSugar(int sugar) {
		this.sugar = sugar;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
	
	
}
