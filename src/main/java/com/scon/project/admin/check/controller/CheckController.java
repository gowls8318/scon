package com.scon.project.admin.check.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.check.model.dto.CheckDTO;
import com.scon.project.admin.check.model.service.CheckService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/check")
public class CheckController {
	
	private CheckService checkService;
	
	@Autowired
	public CheckController(CheckService checkService) {
		this.checkService = checkService;
	}
	
	/* 날짜별 반 출석 조회 */
	@GetMapping("/chkViewClass")
	public ModelAndView selectAllClassDayList(ModelAndView mv) throws Exception {
		
		List<CheckDTO> checkClassList = checkService.selectAllClassList();
		List<ClassDTO> classList = checkService.selectClassList();
		
		mv.addObject("checkClassList", checkClassList);
		mv.addObject("classList", classList);
		mv.setViewName("admin/check/chkViewClass");
		
		log.info("checkClassList : {}" , mv);
		
		return mv;
	}
	
	/* 학생별 출석 조회 */
	@GetMapping("/chkViewStudent")
	public ModelAndView selectAllStudentList(ModelAndView mv) throws Exception {
		
		List<CheckDTO> checkStudentList = checkService.selectAllStudentList();
		List<ClassDTO> classList = checkService.selectClassList();
		
		mv.addObject("checkStudentList", checkStudentList);
		mv.addObject("classList", classList);
		mv.setViewName("admin/check/chkViewStudent");
		
		return mv;
	}

	@GetMapping("/chkRegistNs")
	public String chkRegistNoResult() {
		return "admin/check/chkRegistNs";
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
