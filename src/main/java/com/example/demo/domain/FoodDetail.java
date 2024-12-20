package com.example.demo.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	@Column(nullable=true)
	private float fdKcal;
	
	@Column(nullable=true)
	private float fdCarb; //탄수화물
	
	@Column(nullable=true)
	private float fdPrt; //단백질
	
	@Column(nullable=true)
	private float fdFat; //지방
	
	@Column(nullable=true)
	private float fdSugar; //당류
	
	@Column(nullable=true)
	private float fdNa; //나트륨
	
	
	@OneToOne
	@JoinColumn(name="fuid", nullable=false)
	private Food food;
	
}
