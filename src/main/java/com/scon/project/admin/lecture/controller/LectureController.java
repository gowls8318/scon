package com.scon.project.admin.lecture.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.lecture.model.dto.LectureDTO;
import com.scon.project.admin.lecture.model.service.LectureService;

@Controller
@RequestMapping("/admin")
public class LectureController {

	private LectureService lectureService;
	private MessageSource messageSource;
	
	@Autowired
	public LectureController(LectureService lectureService, MessageSource messageSource) {
		this.lectureService = lectureService;
		this.messageSource = messageSource;
	}
	
	/* 수강 내역 조회용 */
	@GetMapping("/student/studentList")
	public ModelAndView selectAllLectureList(ModelAndView mv) throws Exception {
		
		List<LectureDTO> lectureList = lectureService.selectAllLectureList();
		
		mv.addObject("lectureList", lectureList);
		mv.setViewName("admin/student/studentList");
		
		return mv;
	}
	
	/* 전체 강의 조회용 */
	@GetMapping(value="student/lectureRegist", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<ClassDTO> findAllClassList() throws Exception {
		
		return lectureService.findAllClassList();
	}
	
	/* 강의 정보 조회용 */
//	@GetMapping("/student/lectureRegist")
//	public ModelAndView findClassDetail(ModelAndView mv) throws Exception {
//		
//		List<ClassDTO> classList = lectureService.findClassDetail();
//		
//		mv.addObject("classDetail", classList);
//		mv.setViewName("admin/student/lectureRegist");
//		
//		return mv;
//	}
	
	/* 수강 등록용 */
	@PostMapping("/student/lectureRegist")
	@ResponseBody
	public String insertLecture(@ModelAttribute LectureDTO lec, RedirectAttributes rttr, Locale locale) throws Exception {
		
		lectureService.insertLecture(lec);
		
		return messageSource.getMessage("insertLecture", null, locale);
	}
	
	/* 수강 수정용 */
	@GetMapping("/student/lectureModify")
	@ResponseBody
	public String updateLecturePage(@RequestParam int no, Model model) throws Exception {
		
		model.addAttribute("lecture", lectureService.selectLectureDetail(no));
		
		return "admin/student/lectureModify";
	}
	
	@PostMapping("/student/lectureModify")
	public String modifyLecture(@ModelAttribute LectureDTO lec, RedirectAttributes rttr, Locale locale) throws Exception {
		
		lectureService.modifyLecture(lec);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("updateLecture", null, locale));
		
		return "redirect:/admin/student/studentList";
	}
	
}
