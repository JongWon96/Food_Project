package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 ID
    @Column(name = "U_UID")
    private Long uUid;

    @Column(name = "U_ID", nullable = false, length = 50)
    private String uId;

    @Column(name = "U_Pw", nullable = false, length = 50)
    private String uPw;

    @Column(name = "U_Name", nullable = false, length = 50)
    private String uName;

    @Column(name = "U_Phone", nullable = false)
    private Long uPhone;

    @Column(name = "U_Gender", nullable = false)
    private Integer uGender;  // 1: 남자, 2: 여자

    @Column(name = "U_Age", nullable = false)
    private Integer uAge;

    @Column(name = "U_Weight", nullable = false)
    private Integer uWeight;

    @Column(name = "U_Height", nullable = false)
    private Integer uHeight;

    @Column(name = "U_Goal", nullable = false)
    private Integer uGoal;  // 목표체중

    @Column(name = "U_Style", nullable = false)
    private Integer uStyle;  // 식단 스타일 (예: 1: 저칼로리, 2: 고단백 등)

    @Column(name = "U_Allergy", length = 100)
    private String uAllergy;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FoodDiary> foodDiary = new ArrayList<>();

    // 기본 생성자
    public UserInfo() {
    }

    // Getter & Setter
    public Long getUUid() {
        return uUid;
    }

    public void setUUid(Long uUid) {
        this.uUid = uUid;
    }

    public String getUId(Long uUid) {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getUPw() {
        return uPw;
    }

    public void setUPw(String uPw) {
        this.uPw = uPw;
    }

    public String getUName(Long uUID) {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public Long getUPhone() {
        return uPhone;
    }

    public void setUPhone(Long uPhone) {
        this.uPhone = uPhone;
    }

    public Integer getUGender() {
        return uGender;
    }

    public void setUGender(Integer uGender) {
        this.uGender = uGender;
    }

    public Integer getUAge() {
        return uAge;
    }

    public void setUAge(Integer uAge) {
        this.uAge = uAge;
    }

    public Integer getUWeight() {
        return uWeight;
    }

    public void setUWeight(Integer uWeight) {
        this.uWeight = uWeight;
    }

    public Integer getUHeight() {
        return uHeight;
    }

    public void setUHeight(Integer uHeight) {
        this.uHeight = uHeight;
    }

    public Integer getUGoal() {
        return uGoal;
    }

    public void setUGoal(Integer uGoal) {
        this.uGoal = uGoal;
    }

    public Integer getUStyle() {
        return uStyle;
    }

    public void setUStyle(Integer uStyle) {
        this.uStyle = uStyle;
    }

    public String getUAllergy() {
        return uAllergy;
    }

    public void setUAllergy(String uAllergy) {
        this.uAllergy = uAllergy;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uUid=" + uUid +
                ", uId='" + uId + '\'' +
                ", uPw='" + uPw + '\'' +
                ", uName='" + uName + '\'' +
                ", uPhone=" + uPhone +
                ", uGender=" + uGender +
                ", uAge=" + uAge +
                ", uWeight=" + uWeight +
                ", uHeight=" + uHeight +
                ", uGoal=" + uGoal +
                ", uStyle=" + uStyle +
                ", uAllergy='" + uAllergy + '\'' +
                '}';
    }
}


