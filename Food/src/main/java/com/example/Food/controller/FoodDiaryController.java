package com.example.Food.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
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

import com.example.Food.domain.Food;
import com.example.Food.domain.FoodDetail;
import com.example.Food.domain.UserInfo;
import com.example.Food.dto.FoodCalendarDTO;
import com.example.Food.dto.FoodKcalDTO;
import com.example.Food.service.FoodDiaryService;
import com.example.Food.service.FoodService;

import jakarta.servlet.http.HttpSession;

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

    @GetMapping
    public String loadFoodDiaryPage(@RequestParam(value = "date", required = false)
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                    Model model) {
        if (date == null) {
            date = LocalDate.now(); // 날짜가 없으면 오늘 날짜로 설정
        }

        // 식사별 데이터 가져오기
        List<FoodCalendarDTO> breakfastList = foodDiaryService.getFoodDiaryByDateAndMeal(date, 1);
        List<FoodCalendarDTO> lunchList = foodDiaryService.getFoodDiaryByDateAndMeal(date, 2);
        List<FoodCalendarDTO> dinnerList = foodDiaryService.getFoodDiaryByDateAndMeal(date, 3);
        List<FoodCalendarDTO> snackList = foodDiaryService.getFoodDiaryByDateAndMeal(date, 4);

        // 합계 계산
        int totalKcal = 0, totalCarb = 0, totalPrt = 0, totalFat = 0;
        for (List<FoodCalendarDTO> list : List.of(breakfastList, lunchList, dinnerList, snackList)) {
            for (FoodCalendarDTO dto : list) {
                totalKcal += dto.getKcal() != null ? dto.getKcal() : 0;
                totalCarb += dto.getCarb() != null ? dto.getCarb() : 0;
                totalPrt += dto.getPrt() != null ? dto.getPrt() : 0;
                totalFat += dto.getFat() != null ? dto.getFat() : 0;
            }
        }

        // 요일 계산
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN); // 한글 요일

        // Model에 데이터 추가
        model.addAttribute("selectedDate", date);
        model.addAttribute("dayName", dayName);
        model.addAttribute("breakfastList", breakfastList);
        model.addAttribute("lunchList", lunchList);
        model.addAttribute("dinnerList", dinnerList);
        model.addAttribute("snackList", snackList);
        model.addAttribute("totalKcal", totalKcal);
        model.addAttribute("totalCarb", totalCarb);
        model.addAttribute("totalPrt", totalPrt);
        model.addAttribute("totalFat", totalFat);

        return "FoodDiary";
    }


    // 음식 검색
    @GetMapping("/search")
    @ResponseBody
    public FoodKcalDTO searchFoodByName(@RequestParam("name") String name) {
        return foodService.getFoodKcalByName(name);
    }

    @PostMapping("/add")
    public String addFoodDiary(@ModelAttribute FoodCalendarDTO foodCalendarDTO, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("loginUser");
        if (user == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        foodCalendarDTO.setUserId(user.getUuid()); // 세션에서 가져온 U_UID 설정
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
