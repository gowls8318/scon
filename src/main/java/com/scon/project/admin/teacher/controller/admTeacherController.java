package com.scon.project.admin.teacher.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequestMapping("/admin")
public class admTeacherController {
	
	@GetMapping("/teacherRegist")
	public String teacherRegistForm() {
		return "admin/teacher/teacherRegist";
	}
	
	@GetMapping("/teacherList")
	public String teacherList() {
		return "admin/teacher/teacherList";
	}
	
	

}
