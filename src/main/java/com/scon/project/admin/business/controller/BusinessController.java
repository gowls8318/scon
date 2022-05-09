package com.scon.project.admin.business.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/business/insertBusinessInfo")
	public String insertbusinessInfo(@RequestParam int no, Model model) {
		model.addAttribute("business", businessService.insertBusinessInfo(null));
		return "admin/business/businessInfoInsert";
	}
	
	@PostMapping("/business/insertBusinessInfo")
	public String insertbusinessInfo(@ModelAttribute BusinessDTO bus, RedirectAttributes rttr) {
	
		businessService.insertBusinessInfo(bus);
		rttr.addFlashAttribute("result", bus);
		return "redirect:admin/business/businessInfo";
	}
	

}
