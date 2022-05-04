package com.scon.project.admin.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdminMainController {
	
	@GetMapping("/admin")
	public String main() {
		return "/admin/main/admMain";
	}
	
	@GetMapping("/denied")
	public String denied() {
		return "/common/denied";
	}
	
}
