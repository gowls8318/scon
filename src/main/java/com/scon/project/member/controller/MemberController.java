//package com.scon.project.member.controller;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Configuration
//@RequestMapping("/member")
//public class MemberController {
//	
//	@GetMapping("/login") 
//	public String memberLogin() { 
//		return "/member/login"; 
//	}
//	
//	@PostMapping("/login")
//	public void loginForm(@RequestParam(required=false) String errorMessage, Model model) {
//		model.addAttribute("errorMessage", errorMessage );
//	}
//	
//	@GetMapping("/forgotId")
//	public String forgetId() {
//		return "/member/forgotId";
//	}
//
//	@GetMapping("/forgotPwd")
//	public String forgetPwd() {
//		return "/member/forgotPwd";
//	}
//
//	@GetMapping("/newPwd")
//	public String newPwd() {
//		return "/member/newPwd";
//	}
//
//
//}
