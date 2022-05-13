package com.scon.project.admin.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminMainController {
	
	@GetMapping("/")
	public String main() {
		return "/member/login";
	}
	
	@GetMapping("/admin")
	public String adminmain() {
		return "/admin/main/admMain";
	}
	
	@GetMapping("/denied")
	public String denied() {
		return "/common/denied";
	}
	
}
