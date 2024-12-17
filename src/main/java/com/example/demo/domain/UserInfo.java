	package com.example.demo.domain;
	
	import java.util.List;

import org.hibernate.annotations.DynamicInsert;
	import org.hibernate.annotations.DynamicUpdate;
	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
	@Entity(name = "User_Info")
	public class UserInfo { 
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "U_Uid") 
	    private int uuid;        
	    
	    @Column(name = "U_Id")  
	    private String uid;     
	    
	    @Column(name = "U_Pw")  
	    private String upw;     
	    
	    @Column(name = "U_Name")  
	    private String uname;  
	    
	    @Column(name = "U_Phone")  
	    private int uphone;     
	    
	    @Column(name = "U_Gender")  
	    private int ugender;    
	    
	    @Column(name = "U_Age") 
	    private int uage;      
	    
	    @Column(name = "U_Weight") 
	    private double uweight;   
	    
	    @Column(name = "U_Height") 
	    private double uheight;    
	    
	    @Column(name = "U_goal") 
	    private int ugoal;
	    
	    @Column(name = "U_style") 
	    private int ustyle;    
	    
	    @Column(name = "U_Allergy")  
	    private String uallergy;	
	   
	}
