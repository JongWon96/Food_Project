package com.example.demo.controller;

import com.example.demo.dto.FoodCalendarDTO;
import com.example.demo.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // 특정 날짜 범위의 이벤트 조회
    @GetMapping("/events")
    public List<FoodCalendarDTO> getEvents(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return calendarService.getEventsInRange(start, end);
    }

    // 새로운 이벤트 추가
    @PostMapping("/event")
    public FoodCalendarDTO addEvent(@RequestBody FoodCalendarDTO foodCalendarDTO) {
        return FoodCalendarDTO.toDto(calendarService.saveFoodDiary(foodCalendarDTO));
    }

    // 이벤트 삭제 (추가 구현 필요)
    @DeleteMapping("/event")
    public String deleteEvent(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                              @RequestParam("userId") int userId,
                              @RequestParam("foodId") int foodId) {
        // 실제 삭제 로직은 Service/Repository에 추가 필요
        return "Event deletion endpoint (logic to be implemented).";
    }
}
