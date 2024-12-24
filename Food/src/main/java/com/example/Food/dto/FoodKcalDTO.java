package com.example.Food.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class FoodKcalDTO {
    private String foodName;
    private Integer kcal;
}