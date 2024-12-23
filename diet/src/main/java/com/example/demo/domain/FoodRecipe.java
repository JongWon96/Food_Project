package com.example.demo.domain;

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
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate		//변경되는 컬럼만 수정할 수 있도록 함
@Entity
public class FoodRecipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fruid;
	
	@Column(name = "F_ingredient")
	private String fingredient;
	
	@Column(name = "F_Recipe")
	private String frecipe;
	
	@ManyToOne
	@JoinColumn(name = "F_UID")
	private Food food;
}
