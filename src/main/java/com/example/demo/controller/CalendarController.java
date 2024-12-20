package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.FoodCalendarDTO;
import com.example.demo.service.CalendarService;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // 캘린더 HTML 페이지 로드
    @GetMapping
    public String loadCalendarPage(Model model) {
        return "Calendar"; // HTML 파일 이름
    }

    @GetMapping("/events")
    @ResponseBody
    public List<Map<String, Object>> getEventsInRange(
            @RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and End dates must be provided.");
        }

        List<FoodCalendarDTO> events = calendarService.getEventsInRange(start, end);

        return events.stream()
                .map(event -> Map.<String, Object>of(
                        "title", event.getFoodName() + " (" + event.getDmeal() + ")",
                        "start", event.getDate().toString()
                ))
                .collect(Collectors.toList());
    }

}
