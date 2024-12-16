package com.example.demo.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
public class FoodDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int fdUid;
	private float fdKcal;
	private float fdCarb; //탄수화물
	private float fdPrt; //단백질
	private float fdFat; //지방
	private float fdSugar; //당류
	private float fdNa; //나트륨
	//private String fdAllergy; //알레르기
	
	@OneToOne
	@JoinColumn(name="fUid", nullable=false)
	private Food food;
	
}
