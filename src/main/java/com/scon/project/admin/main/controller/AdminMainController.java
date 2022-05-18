package com.scon.project.admin.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.main.model.dto.StudentCount;
import com.scon.project.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminMainController {
	
	private MemberService memberService;
	
	@Autowired
	public AdminMainController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/")
	public String main() {
		return "/member/login";
	}
	
	@GetMapping("/admin")
	public ModelAndView  adminmain(ModelAndView mv) {
		
		StudentCount count= memberService.findStudentCount();
		
		log.info("원생 수 추이 확인 : {}", count);
		
		mv.addObject("count", count);
		mv.setViewName("/admin/main/admMain");
		
		return mv;
	}
	
	@GetMapping("/denied")
	public String denied() {
		return "/common/denied";
	}
	
}
