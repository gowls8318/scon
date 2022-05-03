package com.scon.project.admin.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminMainController {
	
	@GetMapping(value = {"/", "/main"})
	public String main() {
		return "admin/main/admMain";
	}
	
	@PostMapping(value="/")
	public String redirectMain() {
		return "redirect:/";
	}
	
}
