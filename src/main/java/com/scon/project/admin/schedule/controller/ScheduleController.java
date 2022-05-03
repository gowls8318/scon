package com.scon.project.admin.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scon.project.admin.schedule.model.service.ScheduleService;

@Controller
@RequestMapping("/admin")
public class ScheduleController {

	private ScheduleService scheduleService;
	
	@Autowired
	public ScheduleController(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
		
	@GetMapping("/schedule")
	public String schedule() {
		
		return "admin/schedule/schedule";
	}	

	
}