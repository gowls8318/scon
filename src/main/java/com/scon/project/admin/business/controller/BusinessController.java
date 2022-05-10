package com.scon.project.admin.business.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	//수정
	@GetMapping("/business/updateBusinessInfo")
	public ModelAndView updateBusinessInfo(ModelAndView mv) {
		BusinessDTO updateBusinessInfo = businessService.updateBusinessInfo();
		mv.addObject("BusinessDTO", updateBusinessInfo);
		mv.setViewName("admin/business/updateBusinessInfo");
		return mv;
	}
	
	@PostMapping("/business/updateBusinessInfo")
	public String updateBusinessInfo(BusinessDTO businessDTO) {
		
		businessService.updateBusinessInfo();
		return "redirect:/admin/business/businessInfo";
	}
	
	/*@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public ModelAndView updateBusinessInfo(@RequestParam ModelAndView mv) throws Exception {
		mv.addObject("BusinessDTO", updateBud=dini);
	}*/

}
