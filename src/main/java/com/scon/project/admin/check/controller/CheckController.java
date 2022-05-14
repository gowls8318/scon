package com.scon.project.admin.check.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.check.model.dto.CheckDTO;
import com.scon.project.admin.check.model.service.CheckService;

@Controller
@RequestMapping("/admin/check")
public class CheckController {
	
	private CheckService checkService;
	
	@Autowired
	public CheckController(CheckService checkService) {
		this.checkService = checkService;
	}
	
	/* 반별 출결 조회 */
	@GetMapping("/chkViewClass")
	public ModelAndView selectAllStudentList(ModelAndView mv) throws Exception {
		
		List<CheckDTO> checkList = checkService.selectAllStudentList();
		
		mv.addObject("checkList", checkList);
		mv.setViewName("admin/check/chkViewClass");
		
		return mv;
	}
//
//	@GetMapping("/chkViewClassNs")
//	public String chkViewClassNoResult() {
//		return "admin/check/chkViewClassNs";
//	}
//	
//	@GetMapping("/chkRegist")
//	public String chkRegist() {
//		
//		return "admin/check/chkRegist";
//	}
	
	
	
//	@GetMapping("/chkRegist")
//	public void chkRegist(Model model) {
//	model.addAttribute("getClassList", CheckService.getClassList());
//	}
	
	@GetMapping("/chkRegistNs")
	public String chkRegistNoResult() {
		return "admin/check/chkRegistNs";
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
