package com.example.demo.controller;

import com.example.demo.dto.FoodCalendarDTO;
import com.example.demo.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CalendarPageController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarPageController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendar")
    public String getCalendarPage(Model model) {
        YearMonth currentMonth = YearMonth.now();
        LocalDate start = currentMonth.atDay(1);
        LocalDate end = currentMonth.atEndOfMonth();

        // Fetch events for the current month
        List<FoodCalendarDTO> events = calendarService.getEventsInRange(start, end);

        // Prepare calendar days
        List<DayView> calendar = new ArrayList<>();
        for (int day = 1; day <= currentMonth.lengthOfMonth(); day++) {
            LocalDate date = currentMonth.atDay(day);
            List<FoodCalendarDTO> dayEvents = new ArrayList<>();
            for (FoodCalendarDTO event : events) {
                if (event.getDate().isEqual(date)) {
                    dayEvents.add(event);
                }
            }
            calendar.add(new DayView(date, dayEvents));
        }

        model.addAttribute("calendar", calendar);
        return "calendar"; // calendar.html
    }

    // 내부 클래스 (날짜별 데이터를 저장하는 구조)
    public static class DayView {
        private final LocalDate date;
        private final List<FoodCalendarDTO> events;

        public DayView(LocalDate date, List<FoodCalendarDTO> events) {
            this.date = date;
            this.events = events;
        }

        public LocalDate getDate() {
            return date;
        }

        public List<FoodCalendarDTO> getEvents() {
            return events;
        }
    }
}
