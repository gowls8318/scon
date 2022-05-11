package com.scon.project.admin.business.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.business.model.dto.BusinessDTO;
import com.scon.project.admin.business.model.service.BusinessService;

@Controller
@RequestMapping("/admin")
public class BusinessController {
	
	private BusinessService businessService;
	
	@Autowired
	public BusinessController(BusinessService businessService) {
		this.businessService = businessService;
	}
	
	//조회
	@GetMapping("/business/businessInfo")
	public ModelAndView businessInfo(ModelAndView mv) {
		BusinessDTO businessInfo = businessService.selectBusinessInfo();
		mv.addObject("BusinessDTO", businessInfo);
		mv.setViewName("admin/business/businessInfo");
		return mv;
	}
	
	//등록
	@GetMapping("/business/insertBusinessInfo")
	public String insertBusinessInfo(@RequestParam BusinessDTO business, Model model) {
		
		BusinessDTO insertBusinessInfo = businessService.insertBusinessInfo(business);
		
		model.addAttribute("BusinessDTO", insertBusinessInfo);
		
		return "admin/business/insertBuinessInfo";
	}
	
	@PostMapping("/consultant/insertBusinessInfo")
	public String insertBusinessInfoForm(@RequestParam BusinessDTO business, Model model) {
		
		BusinessDTO insertBusinessInfo = businessService.insertBusinessInfo(business);
		
		model.addAttribute("BusinessDTO", insertBusinessInfo);
		
		return "admin/business/buinessInfo";
		
	}
//	//입력
//	@RequestMapping(value="/business/updateBusinessInfo", method = RequestMethod.GET)
//	public String insertBusinessInfoGet(ModelAndView mv) {
//		mv.addObject("BusinessDTO", businessService.selectBusinessInfo());
//		return "/business/updateBusinessInfo";
//	}
//	
//	@RequestMapping(value="/business/updateBusinessInfo", method = RequestMethod.POST)
//	public String insertBusinessInfoPost(ModelAndView mv) {
//		businessService.updateBusinessInfo(mv);
//		return "/business/businessInfo";
//	}
//	
//	//수정
//	@GetMapping("/business/updateBusinessInfo")
//	public 
//	}
//	
//	@PostMapping("/business/updateBusinessInfo")
//	public ModelAndView updateBusinessInfo(BusinessDTO businessDTO) {
//		
//		businessService.updateBusinessInfo();
//		return "redirect:/admin/business/businessInfo";
//	}
	
	
	

}
