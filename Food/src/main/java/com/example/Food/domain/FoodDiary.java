package com.example.Food.domain;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert   
@DynamicUpdate
@Getter
@Setter
@Entity
@Table(name = "FoodDiary")
public class FoodDiary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 ID
	@Column(name = "D_UID")
	private int duid;

	@Column(name = "D_Date")
	private LocalDate ddate;

	@Column(name = "D_Meal")
	private Integer dmeal;

	// 연관관계 매핑
	@OneToOne
	@JoinColumn(name = "F_UID")
	private Food food;

	@ManyToOne
	@JoinColumn(name = "U_UID")
    @ToString.Exclude // 순환 참조 방지
	private UserInfo userInfo;
}
