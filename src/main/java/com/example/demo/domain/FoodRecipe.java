package com.example.demo.domain;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
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
	
	@Column(length=500)
	private String fIngre;
	
	@Column(length=4000, nullable = true)
	private String fReicpe;
	
	@ColumnDefault("0")
	private int allergy;
	
//	@OneToMany
//	@JoinColumn(name="", nullable=false)
//	private List<Food> food;
	
}
