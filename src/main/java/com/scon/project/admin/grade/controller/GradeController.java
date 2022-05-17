package com.scon.project.admin.grade.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.admin.grade.model.dto.GradeDTOList;
import com.scon.project.admin.grade.model.service.GradeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
//@SessionAttributes("clsId")
public class GradeController {
	
	private GradeService gradeService;
	
	@Autowired
	public GradeController(GradeService gradeSerivce) {
		this.gradeService = gradeSerivce;
	}

	/* 성적 조회 complete (gradeList) */
	@GetMapping("/gradeList")
	public ModelAndView findGradeList(@RequestParam int clsId, ModelAndView mv) {
		
		List<GradeDTO> gradeList = gradeService.findAllGrade(clsId);
		
		log.info("gradeList : {} ", gradeList);
		
		mv.addObject("gradeList", gradeList);
		mv.setViewName("admin/grade/gradeList");
		
//		log.info("gradeList toString : {} ", gradeList.toString());
		
		return mv;
	}
	
	/* 성적 수정 complete (gradeList) */
	@PostMapping("/grade")
	public String updateGrade(GradeDTO gradeDTO, HttpServletRequest request) throws Exception {
		
		int result = gradeService.updateGrade(gradeDTO);
		String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
		return "redirect:"+ referer;
	}
	
	/* 성적 등록 학생 조회 complete (insertGrade) */
	@GetMapping("/insertGrade")
	public ModelAndView findAllStudent(@RequestParam int clsId, ModelAndView mv){
		
		List<GradeDTO> memberList = gradeService.findAllStudent(clsId);
		
		mv.addObject("memberList", memberList);
		mv.setViewName("admin/grade/insertGrade");
		
		return mv;
	}
	
	/* 성적 입력 complete (insertGrade) */
	@PostMapping("/insertGrade") //@RequestParam int clsId
	public String insertGrade(GradeDTOList gradeList, HttpServletRequest request) throws Exception {
	//	log.info(gradeList.getGradeList().toString());
		boolean result = gradeService.insertGradeList(gradeList.getGradeList());
		String referer = request.getHeader("Referer");
		return "redirect:"+ referer;
	}

	
	/* 성적 삭제 */
//	@PostMapping("/gradeList")
//	public String deleteGrade(@RequestParam(value="gradeId[]") int[] gradeId, HttpServletRequest request) throws Exception {
//		
//		int result = gradeService.deleteGrade(gradeId);
//		
//		for(int i=0; i<gradeId.length; i++) {
//			
//		}
//		
//		String referer = request.getHeader("Referer");
//		return "redirect:" + referer;
//	}

	//성적 삭제 테스트 중~~~~~
//	@PostMapping("/gradeList")
//	public String deleteGrade(@RequestParam(value="gradeId[]") List<String> deleteList, HttpServletRequest request) {
//		
//		int result = gradeService.deleteGrade(deleteList);
//		String referer = request.getHeader("Referer");
//		return "redirect:" + referer;
//		
//	}
	


	/* 석차별 성적 조회 */
	@GetMapping("/gradeRank")
	public String gradeRankTest() {
		return "admin/grade/gradeRank";
	}
	
	@GetMapping("/modifyBoard")
	public String modifyBoard() {
		return "admin/grade/modifyTask";
	}
	
	
}
