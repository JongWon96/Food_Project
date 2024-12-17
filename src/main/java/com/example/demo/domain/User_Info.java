package com.example.demo.domain;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor	
@DynamicInsert
@DynamicUpdate
@Entity
@Getter
@Setter
public class User_Info {
	
	@Id
	private int U_Uid;
	
	private String U_Id;
	
	private String U_Pw;
	
	private String U_Name;
	
	private int Phone;
	
	private int U_Gender;
	
	private int U_Age;
	
	private int U_Weight;
	
	private int U_Height;
	
	private int U_goal;
	
	private int Style;
	
	private String U_Allergy;
	
    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodDiary> foodDiary = new ArrayList<>();
	
}
