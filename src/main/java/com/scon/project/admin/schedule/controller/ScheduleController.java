package com.scon.project.admin.schedule.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scon.project.admin.schedule.model.service.ScheduleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RestController
@RequestMapping("/admin")
public class ScheduleController {

	private ScheduleService scheduleService;
	
	@Autowired
	public ScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	
	@GetMapping("/schedule")
	public String schedule(Model model) {		
		
		
		
		
		
		return "admin/schedule/schedule";
	}	

	
}