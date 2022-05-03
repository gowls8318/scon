package com.scon.project.admin.progress.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProgressController {

	@GetMapping("/progressList")
	public String progressList() {
		return "/admin/progress/progressList";
	}
	
	
	
	
	
}
