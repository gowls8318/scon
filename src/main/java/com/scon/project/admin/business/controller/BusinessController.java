package com.scon.project.admin.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/business/businessInfo")
	public ModelAndView businessInfo(ModelAndView mv) {
		BusinessDTO businessInfo = businessService.selectBusinessInfo();
		mv.addObject("BusinessDTO", businessInfo);
		mv.setViewName("admin/business/businessInfo");
		return mv;
	}
	

}
