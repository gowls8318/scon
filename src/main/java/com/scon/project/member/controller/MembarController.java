package com.scon.project.member.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequestMapping("/member")
public class MembarController {
	
	@GetMapping("/login")
	public String memberLogin() {
		return "/member/login";
	}
	
	@GetMapping("/forgotId")
	public String forgetId() {
		return "/member/forgotId";
	}

	@GetMapping("/forgotPwd")
	public String forgetPwd() {
		return "/member/forgotPwd";
	}

	@GetMapping("/newPwd")
	public String newPwd() {
		return "/member/newPwd";
	}


}
