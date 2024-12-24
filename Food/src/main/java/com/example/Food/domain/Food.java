package com.example.Food.domain;

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
@Table(name = "Food")
@Getter
@Setter
@ToString(exclude = {"foodDetail", "foodRecipes"})
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_UID")
    private int fuid;

    @Column(name = "F_Name")
    private String fname;

    @Column(name = "F_Img")
    private String fimg;

    @Column(name = "F_Category")
    private String fcategory;

    @OneToOne(mappedBy = "food", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FoodDetail foodDetail;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodRecipe> foodRecipes = new ArrayList<>();
}
