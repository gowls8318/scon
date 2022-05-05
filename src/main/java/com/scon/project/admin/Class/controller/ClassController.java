package com.scon.project.admin.Class.controller;

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

import com.scon.project.admin.Class.service.ClassService;
import com.scon.project.member.model.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j // log테스트
@Controller
@RequestMapping("/admin")
public class ClassController {

	private ClassService classService;
	private MessageSource messageSource; // log테스트

	@Autowired
	public ClassController(ClassService classService, MessageSource messageSource) {
		this.classService = classService;
		this.messageSource = messageSource;
	}
	
	
	// 강의 등록용 화면 이동
	@GetMapping("/classRegist")
	public String getDashBoard2() {

		return "admin/class/insertClass";
	}
	

	/*
	@GetMapping("/classList")
	public String getDashBoard3() {

		return "admin/class/selectClass";
	}
	*/

	@GetMapping("/classUpdate")
	public String getDashBoard4() {

		return "admin/class/updateClass";
	}
	

	/*----------------------------------------------------*/

	
	// 강의리스트조회
	@GetMapping("classList")
	public ModelAndView selectClass (ModelAndView mv) {
		
		List<ClassDTO> classList = classService.selectClass();
		
		mv.addObject("classList", classList);
		mv.setViewName("admin/class/selectClass");
		return mv;
	}
	
	
	//강의상세조회
	@PostMapping("classList")
	public ModelAndView selectAllClass (ModelAndView mv) {
		
		List<ClassDTO> classAllList = classService.selectAllClass();
		
		mv.addObject("classAllList", classAllList);
		mv.setViewName("admin/class/selectClass");
		return mv;
	}

	
	
	
	@PostMapping("classRegist")
	public String registClass(@ModelAttribute ClassDTO classDTO, RedirectAttributes rttr, Locale locale)
			throws Exception {

		log.info("등록 요청 강의 : {}", classDTO);
		MemberDTO member = new MemberDTO();
		member.setId("director");
		classDTO.setMember(member);
		classService.registClass(classDTO);

//		rttr.addFlashAttribute("successMessage", messageSource.getMessage("ClassRegist", null, locale));

		return "redirect:/admin/classList";

	}
	
	
	

} // class
