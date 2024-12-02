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
@DynamicUpdate	
@Entity
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
	

}
