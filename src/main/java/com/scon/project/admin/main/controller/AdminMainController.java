package com.scon.project.admin.main.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.admin.consultant.model.service.ConsultantService;
import com.scon.project.admin.main.model.dto.StudentCount;
import com.scon.project.common.paging.Criteria;
import com.scon.project.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminMainController {
	
	private MemberService memberService;
	private ConsultantService consultantService;
	
	@Autowired
	public AdminMainController(MemberService memberService, ConsultantService consultantService) {
		this.memberService = memberService;
		this.consultantService = consultantService;
	}
	
	@GetMapping("/")
	public String main() {
		return "/member/login";
	}
	
	@GetMapping("/admin")
	public ModelAndView  adminmain(@ModelAttribute Criteria cri, ModelAndView mv) throws Exception {
		
		StudentCount count= memberService.findStudentCount();
		
		log.info("원생 수 추이 확인 : {}", count);
		
		List<ConsultantDTO> consultantHopeList = consultantService.selectAllConsultantHopeList(cri);
		log.info("상담 신청 내역 : {}", consultantHopeList);
		mv.addObject("consultantHopeList", consultantHopeList);
		
		mv.addObject("count", count);
		mv.setViewName("/admin/main/admMain");
		
		return mv;
	}
	
	@GetMapping("/denied")
	public String denied() {
		return "/common/denied";
	}
	
	/* 예외 처리 */
	@ExceptionHandler(value = Exception.class)
	public String exception(Exception e, Model model) {
		
		model.addAttribute("errorMessage", e.getMessage());
		
		return "common/adminError";
	}
	
}
