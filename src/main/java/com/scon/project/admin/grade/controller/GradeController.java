package com.scon.project.admin.grade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/grade")
public class GradeController {

	@GetMapping("/gradeList")
	public String main() {
		return "admin/grade/gradeList";
	}
	
	@GetMapping("/gradeRank")
	public String gradeRankTest() {
		return "admin/grade/gradeRank";
	}
	
	@GetMapping("/insertGrade")
	public String insertGradeTest() {
		return "admin/grade/insertGrade";
	}
	
	
	
	
}
