package com.example.demo.dto;

import lombok.ToString;

@ToString
public class UserBmrDto {
    private String name;
    private Integer age;
    private Double weight;
    private Double height;
    private Integer gender;
    private Double bmr;

    public UserBmrDto(String name, Integer age, Double weight, Double height, Integer gender, Double bmr) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.bmr = bmr;
    }

    // Getter & Setter
    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }

    public Integer getGender() {
        return gender;
    }

    public Double getBmr() {
        return bmr;
    }
    
    
}