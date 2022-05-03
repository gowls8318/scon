package com.scon.project.student.main.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequestMapping("/")
public class StdMainController {
	
	@GetMapping("/student")
	public String studentMain() {
		return "/student/main/stdMain";
	}

}
