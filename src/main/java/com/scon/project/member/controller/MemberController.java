package com.scon.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scon.project.member.model.dto.MemberDTO;
import com.scon.project.member.model.service.MemberService;

@Configuration
@RequestMapping("/member")
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/login") 
	public String memberLogin() { 
		return "/member/login"; 
	}
	
	@PostMapping("/login")
	public void loginForm(@RequestParam(required=false) String errorMessage, Model model) {
		model.addAttribute("errorMessage", errorMessage );
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

	//아이디 중복 체크
	@ResponseBody
	@RequestMapping(value="/checkId", method= RequestMethod.POST)
	public int checkId(MemberDTO member) {
		
		int count = memberService.checkId(member);

		return count;
	}

}
