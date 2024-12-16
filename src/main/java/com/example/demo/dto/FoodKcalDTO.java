package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodKcalDTO {
    private String foodName;
    private Integer kcal;
}