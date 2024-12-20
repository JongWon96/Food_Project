package com.example.Food.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "U_goal")
    private int ugoal;

    @Column(name = "U_Style")
    private int ustyle;

    @Column(name = "U_Allergy")
    private int uallergy;

    //보드삭제시 해당 댓글 자동삭제코드
    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<UserChange> userchange;




}