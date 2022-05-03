package com.scon.project.admin.grade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.admin.grade.model.service.GradeService;

@Controller
@RequestMapping("/admin")
public class GradeController {
	
	private GradeService gradeService;
	
	@Autowired
	public GradeController(GradeService gradeSerivce) {
		this.gradeService = gradeSerivce;
	}

/*	@GetMapping("/gradeList")
	public String main() {
		return "admin/grade/gradeList";
	} */
	
	@GetMapping("/gradeList")
	public ModelAndView findGradeList(ModelAndView mv) {
		
		List<GradeDTO> gradeList = gradeService.findAllGrade();
		
		mv.addObject("gradeList", gradeList);
		mv.setViewName("admin/grade/gradeList");
		
		return mv;
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
