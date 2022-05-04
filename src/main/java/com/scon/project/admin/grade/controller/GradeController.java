package com.scon.project.admin.grade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.grade.model.dto.GradeDTO;
import com.scon.project.admin.grade.model.service.GradeService;
import com.scon.project.member.model.dto.MemberDTO;

@Controller
@RequestMapping("/admin")
//@SessionAttributes("clsId")
public class GradeController {
	
	private GradeService gradeService;
	
	@Autowired
	public GradeController(GradeService gradeSerivce) {
		this.gradeService = gradeSerivce;
	}

//	@GetMapping("/gradeList")
//	public String main() {
//		return "admin/grade/gradeList";
//	} 
	
	@GetMapping("/gradeList")
	public ModelAndView findGradeList(@RequestParam int clsId, ModelAndView mv) {

		List<GradeDTO> gradeList = gradeService.findAllGrade(clsId);
		
		mv.addObject("gradeList", gradeList);
		mv.setViewName("admin/grade/gradeList");
		
		return mv;
	}

	/* 성적 등록 페이지 (학생 조회) */
//	@GetMapping(value="insertGrade", produces="application/json; charset=UTF-8")
//	@ResponseBody
//	public List<MemberDTO> findAllStudent(@RequestParam int clsId){
//		return gradeService.findAllStudent(clsId);
//	}
	
	/* 성적 등록용 조회 페이지*/
	@GetMapping("/insertGrade")
	public String insertGradeList() {
		return "admin/grade/insertGrade";
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
