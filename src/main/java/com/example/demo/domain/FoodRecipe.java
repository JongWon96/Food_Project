package com.example.demo.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Entity;
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
@DynamicUpdate		//변경되는 컬럼만 수정할 수 있도록 함
@Entity
public class FoodRecipe {

	@Id
	private int frUid;
	
	private String rfName;
	private String fIngre;
	private String fReicpe;
	
//	@OneToMany
//	@JoinColumn(name="", nullable=false)
//	private List<Food> food;
	
}
