package com.example.demo.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
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
@DynamicUpdate	
@Entity
public class Food {
	
	@Id
	private int fuid;
	
	private String fimg;
	private String fname;
	private String fcategory;

	@ManyToOne
	@JoinColumn(name="frUid", nullable=false)
	private FoodRecipe foodRecipe;
}
