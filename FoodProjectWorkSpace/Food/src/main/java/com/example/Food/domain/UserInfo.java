package com.example.Food.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "User_Info")
public class UserInfo {

    @Id
    @Column(name = "U_Uid")
    private int uuid;

    @Column(name = "U_Id")
    private String uid;

    @Column(name = "U_Pw")
    private String upw;

    @Column(name = "U_Name")
    private String uname;

    @Column(name = "Phone")
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

    @Column(name = "Style")
    private int ustyle;

    @Column(name = "U_Allergy")
    private String uallergy;




}