package com.scon.project.admin.consultant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@GetMapping("consultant-hope-list")
	public ModelAndView selectAllConsultantHopeList(ModelAndView mv) {
		
		List<ConsultantDTO> consultantHopeList = consultantService.selectAllConsultantHopeList();
		
		mv.addObject("consultantHopeList", consultantHopeList);
		mv.setViewName("admin/consultant/hopeList");
		
		return mv;
	}
	
	
}
