package com.scon.project.admin.grade.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.admin.grade.model.service.GradeService;

@Controller
@RequestMapping("/admin")
//@SessionAttributes("clsId")
public class GradeController {
	
	private GradeService gradeService;
	
	@Autowired
	public GradeController(GradeService gradeSerivce) {
		this.gradeService = gradeSerivce;
	}

	/* 성적 조회 complete */
	@GetMapping("/gradeList")
	public ModelAndView findGradeList(@RequestParam int clsId, ModelAndView mv) {

		List<GradeDTO> gradeList = gradeService.findAllGrade(clsId);
		
		mv.addObject("gradeList", gradeList);
		mv.setViewName("admin/grade/gradeList");
		
		return mv;
	}
	
	/* 성적 수정 complete */
	@PostMapping("/grade")
	public String updateGrade(GradeDTO gradeDTO, HttpServletRequest request) throws Exception {
		
		int result = gradeService.updateGrade(gradeDTO);
		String referer = request.getHeader("Referer"); // 헤더에서 이전 페이지를 읽는다.
		return "redirect:"+ referer;
	}
	
	/* 성적 등록 페이지 (학생 조회) complete */
	@GetMapping("/insertGrade")
	public ModelAndView findAllStudent(@RequestParam int clsId, ModelAndView mv){
		
		List<GradeDTO> memberList = gradeService.findAllStudent(clsId);
		
		mv.addObject("memberList", memberList);
		mv.setViewName("admin/grade/insertGrade");
		
		return mv;
	}
	
	/* 성적 등록 */
	@PostMapping("/insertGrade")
	public String insertGrade(GradeDTO gradeDTO, HttpServletRequest request) throws Exception {
		
		boolean result = gradeService.insertGradeList(gradeDTO);
		String referer = request.getHeader("Referer");
		return "redirect:"+ referer;
		
	}
	
	/* 성적 등록 이후 포워딩 */
//	@PostMapping("/insertGrade")
//	public String insertGrade(@ModelAttribute GradeDTO grade, RedirectAttributes rttr) throws Exception {
//		
//		gradeService.insertGradeList(grade);
//		
//		rttr.addFlashAttribute("successMessage", "성적 등록이 완료 되었습니다.");
//		
//		return "redirect:/admin/grade/gradeList";
//	}
	
	

	/* 성차별 성적 조회 */
	@GetMapping("/gradeRank")
	public String gradeRankTest() {
		return "admin/grade/gradeRank";
	}
	
	
}
