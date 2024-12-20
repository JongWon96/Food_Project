package com.example.demo.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Entity
@Table(name = "Food_Detail")
@Getter
@Setter
@ToString(exclude = "food")
public class FoodDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FD_UID")
    private int fduid;

    @Column(name = "FD_kcal")
    private int kcal;

	@Column(name = "FD_carb")
	private int carb;

	@Column(name = "FD_prt")
	private int prt;

	@Column(name = "FD_fat")
	private int fat;

	@Column(name = "FD_sugar")
	private int sugar;

	@Column(name = "FD_allergy")
	private String allergy;

	@OneToOne
	@JoinColumn(name = "F_UID")
	private Food food;
}
