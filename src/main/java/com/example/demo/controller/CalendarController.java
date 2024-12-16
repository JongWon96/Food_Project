package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
	
@Controller
public class CalendarController {

	@Value("${google.calendar.api.key}")
	private String googleApiKey;
	
	@GetMapping("/calendar")
	public String getCalendarPage(Model model) {
	    model.addAttribute("googleApiKey", googleApiKey);
	    return "Calendar";
    }
    
    
}
