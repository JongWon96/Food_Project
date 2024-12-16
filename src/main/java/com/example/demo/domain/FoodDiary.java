package com.example.demo.domain;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class FoodDiary {
	
	@Id
	private int dUid;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@ColumnDefault("sysdate")
	@Column(insertable=false)
	private Date dDate;
	
	private int dMeal; // 아침이면 1, 점심이면 2, 저녁이면 3
	
	@ManyToOne
	@JoinColumn(name="fUid", nullable=false)
	private Food food;
	
	@ManyToOne
	@JoinColumn(name="uUid", nullable=false)
	private User_Info user;
}
