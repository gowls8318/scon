package com.scon.project.admin.consultant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.admin.consultant.model.service.ConsultantService;

@Controller
@RequestMapping("/admin")
public class ConsultantController {

	private ConsultantService consultantService;
	
	@Autowired
	public ConsultantController(ConsultantService consultantService) {
		this.consultantService = consultantService;
	}
	
	/* 상담 신청 내역 조회용 */
	@GetMapping("/consultant/hopeList")
	public ModelAndView selectAllConsultantHopeList(ModelAndView mv) {
		
		List<ConsultantDTO> consultantHopeList = consultantService.selectAllConsultantHopeList();
		
		mv.addObject("consultantHopeList", consultantHopeList);
		mv.setViewName("admin/consultant/hopeList");
		
		return mv;
	}
	
	/* 상담 신청 상세 조회용 */
	@GetMapping("/consultant/hopeDetail")
	public String selectHopeDetailPage(int no, Model model) {
		
		model.addAttribute("consultantHopeDetail", consultantService.selectConsultantHopeDetail(no));
		
		return "admin/consultant/hopeDetail";
	}
	
	/* 상담 일지 내역 조회용 */
	@GetMapping("/consultant/list")
	public ModelAndView selectAllConsultantList(ModelAndView mv) {
		
		List<ConsultantDTO> consultantList = consultantService.selectAllConsultantList();
		
		mv.addObject("consultantList", consultantList);
		mv.setViewName("admin/consultant/list");
		
		return mv;
	}
	
	/* 상담 일지 등록용 */
	@GetMapping("/consultant/insertForm")
	public String insertConsultantPage() {
		
		return "admin/consultant/insertForm";
	}
	
	
	
}
