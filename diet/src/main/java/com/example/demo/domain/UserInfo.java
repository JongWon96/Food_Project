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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "User_Info")  // 기존 테이블에 매핑
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
    private String uphone;

    @Column(name = "U_Gender")
    private int ugender;

    @Column(name = "U_Age")
    private int uage;

    @Column(name = "U_Weight")
    private double uweight;

    @Column(name = "U_Height")
    private double uheight;

    @Column(name = "U_Goal")
    private int ugoal;

    @Column(name = "U_Style")
    private int ustyle;

    @Column(name = "U_Allergy")
    private Integer uallergy;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude // 순환 참조 방지
    private List<FoodDiary> foodDiary = new ArrayList<>();
}