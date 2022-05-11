package com.scon.project.admin.lecture.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.lecture.model.dto.LectureDTO;
import com.scon.project.admin.lecture.model.service.LectureService;
import com.scon.project.member.model.dto.MemberDTO;

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
	@GetMapping("/lecture/list")
	public ModelAndView selectAllLectureList(ModelAndView mv) throws Exception {
		
		List<LectureDTO> lectureList = lectureService.selectAllLectureList();
		List<ClassDTO> classList = lectureService.selectAllClassList();
		
		mv.addObject("lectureList", lectureList);
		mv.addObject("classList", classList);
		mv.setViewName("admin/lecture/list");
		
		return mv;
	}
	
	/* 수강 등록용
	 * - 강의 조회, 원생 조회 */
	@GetMapping("/lecture/insertForm")
	public ModelAndView selectAllClassList(ModelAndView mv) throws Exception {
		
		List<ClassDTO> classList = lectureService.selectAllClassList();
		List<MemberDTO> memberList = lectureService.selectAllMemberList();
		
		mv.addObject("classList", classList);
		mv.addObject("memberList", memberList);
		mv.setViewName("admin/lecture/insertForm");
		
		return mv;
	}
	
	/* 수강 등록용 */
	@PostMapping("/lecture/insertForm")
	public String insertLecture(@ModelAttribute LectureDTO lec, RedirectAttributes rttr, Locale locale) throws Exception {
		
		lectureService.insertLecture(lec);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("insertLecture", null, locale));
		
		return "redirect:/admin/lecture/list";
	}
	
}
