package com.example.demo.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodDetail;
import com.example.demo.dto.FoodCalendarDTO;
import com.example.demo.dto.FoodKcalDTO;
import com.example.demo.service.FoodDiaryService;
import com.example.demo.service.FoodService;

@Controller
@RequestMapping("/food-diary")
public class FoodDiaryController {

    private final FoodService foodService;
    private final FoodDiaryService foodDiaryService;

    @Autowired
    public FoodDiaryController(FoodService foodService, FoodDiaryService foodDiaryService) {
        this.foodService = foodService;
        this.foodDiaryService = foodDiaryService;
    }

    // FoodDiary 페이지 로드
    @GetMapping
    public String loadFoodDiaryPage(@RequestParam(value = "date", required = false)
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                    Model model) {
        if (date == null) {
            date = LocalDate.now(); // 날짜가 없으면 오늘 날짜로 설정
        }
        List<FoodCalendarDTO> foodDiaryList = foodDiaryService.getFoodDiaryByDate(date);

        // 요일 계산
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN); // 한글 요일

        model.addAttribute("foodDiaryList", foodDiaryList);
        model.addAttribute("selectedDate", date); // 선택된 날짜
        model.addAttribute("dayName", dayName);   // 요일 이름
        return "FoodDiary";
    }

    // 음식 검색
    @GetMapping("/search")
    @ResponseBody
    public FoodKcalDTO searchFoodByName(@RequestParam("name") String name) {
        return foodService.getFoodKcalByName(name);
    }

    // FoodDiary 추가
    @PostMapping("/add")
    public String addFoodDiary(@ModelAttribute FoodCalendarDTO foodCalendarDTO) {
        foodDiaryService.addFoodDiary(foodCalendarDTO);
        return "redirect:/food-diary?date=" + foodCalendarDTO.getDate();
    }

    // FoodDiary 삭제
    @PostMapping("/delete/{id}")
    public String deleteFoodDiary(@PathVariable Integer id, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        foodDiaryService.deleteFoodDiary(id);
        return "redirect:/food-diary?date=" + date;
    }
    
    @GetMapping("/search/food-id")
    @ResponseBody
    public Integer searchFoodIdByName(@RequestParam("name") String name) {
        return foodService.getFoodByName(name).getFuid(); // Food 엔티티에서 ID 반환
    }
    
    @GetMapping("/search/food-detail")
    @ResponseBody
    public Map<String, Object> searchFoodDetailByName(@RequestParam("name") String name) {
        Food food = foodService.getFoodByName(name);
        if (food != null && food.getFoodDetail() != null) {
            FoodDetail foodDetail = food.getFoodDetail();
            Map<String, Object> response = new HashMap<>();
            response.put("id", food.getFuid());
            response.put("name", food.getFname());
            response.put("kcal", foodDetail.getKcal());
            return response;
        }
        throw new IllegalArgumentException("음식을 찾을 수 없습니다.");
    }
}
