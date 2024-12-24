package com.example.Food.dto;

import java.time.LocalDate;

import com.example.Food.domain.FoodDiary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodCalendarDTO {
    private LocalDate date;  // LocalDate로 변경
    private int foodId;      // 음식 ID
    private int userId;      // 사용자 ID
    private String foodName; // 음식 이름 (선택)
    private int dmeal;      // 아침/점심/저녁/기타(선택)
    private Integer kcal;
    private Integer carb;
    private Integer prt;
    private Integer fat;
    
    
    public FoodCalendarDTO(LocalDate date, Integer dmeal, int foodId, int userId) {
        this.date = date;
        this.dmeal = dmeal;
        this.foodId = foodId;
        this.userId = userId;
    }

    public static FoodCalendarDTO toDto(FoodDiary foodDiary) {
        return new FoodCalendarDTO(
            foodDiary.getDdate(),
            foodDiary.getDmeal(),
            foodDiary.getFood().getFuid(),
            foodDiary.getUserInfo().getUuid()
        );
    }
}
