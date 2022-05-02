package com.scon.project.admin.student.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequestMapping("/admin")
public class admStudentController {
	
	@GetMapping("/studentRegist")
	public String studentRegistForm() {
		return "admin/student/studentRegist";
	}
	
	@GetMapping("/studentList")
	public String studentList() {
		return "admin/student/studentList";
	}


}
