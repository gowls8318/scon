package com.scon.project.admin.check.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scon.project.admin.check.model.service.CheckService;

@Controller
@RequestMapping("/admin/check")
public class CheckController {
	
	@GetMapping("/chkRegist")
	public String chkRegist() {
		return "admin/check/chkRegist";
	}
	
//	@GetMapping("/chkRegist")
//	public void chkRegist(Model model) {
//	model.addAttribute("getClassList", CheckService.getClassList());
//	}
	
	@GetMapping("/chkRegistNs")
	public String chkRegistNoResult() {
		return "admin/check/chkRegistNs";
	}
	
	@GetMapping("/chkViewClass")
	public String chkViewClass() {
		return "admin/check/chkViewClass";
	}

	@GetMapping("/chkViewClassNs")
	public String chkViewClassNoResult() {
		return "admin/check/chkViewClassNs";
	}
	
	@GetMapping("/chkViewStudent")
	public String chkViewStudent() {
		return "admin/check/chkViewStudent";
	}
	
	@GetMapping("/chkViewStudentNs")
	public String chkViewStudentNoResult() {
		return "admin/check/chkViewStudentNs";
	}
	
	@GetMapping("/chkAbsent")
	public String chkAbsent() {
		return "admin/check/chkAbsent";
	}
	
	@GetMapping("/chkAbsenttNs")
	public String chkAbsentNoResult() {
		return "admin/check/chkAbsentNs";
	}

}
