package com.scon.project.admin.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/academy")
public class AcademyController {
	
	@GetMapping("/academyInfo")
	public String academyInfo() {
		return "admin/academy/academyInfo";
	}
	

}
