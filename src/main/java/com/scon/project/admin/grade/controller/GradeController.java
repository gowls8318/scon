package com.scon.project.admin.grade.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	// 성적 조회 (O)(gradeList)
	@GetMapping("/gradeList")
	public ModelAndView findAllGrade(@RequestParam int clsId, ModelAndView mv) {
		
		List<GradeDTO> gradeList = gradeService.findAllGrade(clsId);
		
		log.info("gradeList : {} ", gradeList);
		
		mv.addObject("gradeList", gradeList);
		mv.setViewName("admin/grade/gradeList");
		
		return mv;
	}
	
	// 성적 수정 (O) (gradeList)
	@PostMapping("/grade")
	public String updateGrade(GradeDTO gradeDTO, HttpServletRequest request) throws Exception {
		
		int result = gradeService.updateGrade(gradeDTO);
		String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
		return "redirect:"+ referer;
	}
	
	// 성적 등록 학생 조회 (O) (insertGrade)
	@GetMapping("/insertGrade")
	public ModelAndView findAllStudent(@RequestParam int clsId, ModelAndView mv){
		
		List<GradeDTO> memberList = gradeService.findAllStudent(clsId);
		
		log.info("memberList : {} ", memberList );
		log.info("clsId : {}", clsId);
		
		mv.addObject("memberList", memberList);
		mv.setViewName("admin/grade/insertGrade");
		
		return mv;
	}
	
	// 성적 입력 (O) (insertGrade)
	@PostMapping("/insertGrade") //@RequestParam int clsId
	public String insertGrade(GradeDTOList gradeList, HttpServletRequest request) throws Exception {
		log.info("gradeList : {}",gradeList.getGradeList());
		boolean result = gradeService.insertGradeList(gradeList.getGradeList());
		String referer = request.getHeader("Referer");
		return "redirect:"+ referer;
	}
	
	
	//성적 삭제 (O)
	@PostMapping("/gradeList")
	@ResponseBody
	public void deleteGrade(@RequestParam(value="gradeId[]") List<String> gradeIdArr, @RequestParam int clsId) throws Exception {
		
		int	result = gradeService.deleteGrade(gradeIdArr);
		log.info("gradeIdArr : {}", gradeIdArr);

	}

	
	/* 예외 처리 */
	@ExceptionHandler(value = Exception.class)
	public String exception(Exception e, Model model) {
		
		model.addAttribute("errorMessage", e.getMessage());
		
		return "common/adminError";
	}
	
	
}
