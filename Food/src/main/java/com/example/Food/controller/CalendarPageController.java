package com.example.Food.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Food.domain.UserInfo;
import com.example.Food.dto.FoodCalendarDTO;
import com.example.Food.dto.UserBmrDto;
import com.example.Food.persistence.UserRepository;
import com.example.Food.service.FoodDiaryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CalendarPageController {

    private final FoodDiaryService foodDiaryService;
    private final UserRepository userRepository;

    @Autowired
    public CalendarPageController(FoodDiaryService foodDiaryService, UserRepository userRepository) {
        this.foodDiaryService = foodDiaryService;
        this.userRepository = userRepository;
    }

    @GetMapping("/calendar")
    public String loadCalendarPage(@RequestParam(value = "year", required = false) Integer year,
                                   @RequestParam(value = "month", required = false) Integer month,
                                   HttpSession session,
                                   Model model) {
        UserInfo user = (UserInfo) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/login";
        }

        LocalDate today = LocalDate.now();
        if (year == null) year = today.getYear();
        if (month == null) month = today.getMonthValue();

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());

        // 기초대사량 가져오기
        UserBmrDto userBmrDto = userRepository.findUserInfoByUid(user.getUuid());
        double bmr = userBmrDto.getBmr();

        List<DayView> calendar = new ArrayList<>();
        for (LocalDate date = firstDayOfMonth; !date.isAfter(lastDayOfMonth); date = date.plusDays(1)) {
            int totalKcal = foodDiaryService.getTotalKcalByDate(date);
            double remainingCalories = bmr - totalKcal;

            DayView dayView = new DayView();
            dayView.setDate(date);
            dayView.setBmr((int) bmr);
            dayView.setRemainingCalories((int) remainingCalories);
            calendar.add(dayView);
        }

        model.addAttribute("calendar", calendar);
        model.addAttribute("year", year);
        model.addAttribute("month", month);

        return "Calendar";
    }


    // 내부 클래스 (날짜별 데이터를 저장하는 구조)
    public static class DayView {
        private LocalDate date;
        private int bmr;
        private int remainingCalories;
        private List<FoodCalendarDTO> events; // 추가

        // Getter와 Setter
        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public int getBmr() {
            return bmr;
        }

        public void setBmr(int bmr) {
            this.bmr = bmr;
        }

        public int getRemainingCalories() {
            return remainingCalories;
        }

        public void setRemainingCalories(int remainingCalories) {
            this.remainingCalories = remainingCalories;
        }

        public List<FoodCalendarDTO> getEvents() {
            return events;
        }

        public void setEvents(List<FoodCalendarDTO> events) {
            this.events = events;
        }
    }
}
