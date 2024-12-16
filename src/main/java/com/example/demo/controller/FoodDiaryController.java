package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodDiary;
import com.example.demo.domain.UserInfo;
import com.example.demo.service.FoodDiaryService;
import com.example.demo.service.UserService;

@Controller
public class FoodDiaryController {

    @Autowired
    private FoodDiaryService foodDiaryService;

    @Autowired
    private UserService userService;

    /**
     * FoodDiary.html 로 이동
     * @param userId 사용자 ID
     * @param model  모델 객체
     * @return FoodDiary.html 페이지
     */
    @GetMapping("/food-diary")
    public String getFoodDiaryPage(@RequestParam("userId") Long userId, Model model) {
        // 사용자 정보 가져오기
        UserInfo user = userService.getUserById(userId);
        model.addAttribute("user", user);

        // 사용자 다이어리 데이터 가져오기
        List<FoodDiary> foodDiaryList = foodDiaryService.getDiaryByUser(userId);
        model.addAttribute("foodDiaryList", foodDiaryList);

        return "FoodDiary"; // templates/FoodDiary.html
    }

    /**
     * 음식 추가 처리
     * @param userId 사용자 ID
     * @param foodId 음식 ID
     * @param mealType 식사 유형 (아침, 점심, 저녁 등)
     * @return 리다이렉트 FoodDiary 페이지
     */
    @PostMapping("/food-diary/add-food")
    public String addFoodToDiary(@RequestParam("userId") Long userId,
                                 @RequestParam("foodId") Long foodId,
                                 @RequestParam("mealType") String mealType) {
        foodDiaryService.addFoodToDiary(userId, foodId, mealType);
        return "redirect:/food-diary?userId=" + userId;
    }

    /**
     * 음식 검색 API
     * @param query 검색어
     * @param model 모델 객체
     * @return 검색 결과 페이지 조각 (AJAX 처리 가능)
     */
    @GetMapping("/food-diary/search")
    public String searchFood(@RequestParam("query") String query, Model model) {
        List<Food> searchResults = foodDiaryService.searchFoods(query);
        model.addAttribute("searchResults", searchResults);
        return "foodSearchResults"; // 반환될 템플릿 조각
    }
}