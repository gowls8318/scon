package com.scon.project.admin.check.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.check.model.dto.CheckDTO;
import com.scon.project.admin.check.model.service.CheckService;
import com.scon.project.member.model.dto.MemberDTO;

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
	
	@GetMapping("/chkViewClass")
	public ModelAndView selectAllClassList(@ModelAttribute ClassDTO cls, @ModelAttribute CheckDTO chk, @ModelAttribute MemberDTO member, ModelAndView mv) throws Exception {
	
		log.info("checkClass : {}" , member.getName());
		
		List<CheckDTO> checkClassList = checkService.selectAllClassList(cls, chk, member);
		List<ClassDTO> classList = checkService.selectClassList(cls);
		
		mv.addObject("checkClassList", checkClassList);
		mv.addObject("clsId", cls.getClsId());
		mv.addObject("chkDate", chk.getChkDate());
		mv.addObject("name", member.getName());
		mv.addObject("classList", classList);
		mv.setViewName("admin/check/chkViewClass");
		
		log.info("checkClassList : {}" , checkClassList);
		
		
		
		return mv;
	}
	
//	/* 학생별 출석 조회 */
//	@GetMapping("/chkViewStudent")
//	public ModelAndView selectAllStudentList(ModelAndView mv) throws Exception {
//		
//		List<CheckDTO> checkStudentList = checkService.selectAllStudentList();
//		List<ClassDTO> classList = checkService.selectClassList();
//		
//		mv.addObject("checkStudentList", checkStudentList);
//		mv.addObject("classList", classList);
//		mv.setViewName("admin/check/chkViewStudent");
//		
//		return mv;
//	}

	
	@GetMapping("/chkRegist")
	public ModelAndView checkRegist(@ModelAttribute ClassDTO cls, @ModelAttribute CheckDTO chk, @ModelAttribute MemberDTO member, ModelAndView mv) throws Exception {
		
		List<CheckDTO> checkClassList = checkService.selectAllClassList(cls, chk, member);
		List<ClassDTO> classList = checkService.selectClassList(cls);
		
		mv.addObject("checkClassList", checkClassList);
		mv.addObject("clsId", cls.getClsId());
		mv.addObject("chkDate", chk.getChkDate());
		mv.addObject("name", member.getName());
		mv.addObject("classList", classList);
		mv.setViewName("admin/check/chkRegist");
		
		return mv;
	}
	
	@GetMapping("/chkInsertForm")
	public ModelAndView enterInsertForm(@ModelAttribute ClassDTO cls, ModelAndView mv) throws Exception {
		
		List<ClassDTO> classList = checkService.selectClassList(cls);
		
		mv.addObject("clsId", cls.getClsId());
		mv.addObject("classList", classList);
		mv.setViewName("admin/check/chkInsertForm");
		
		return mv;
	}
	
//	@PostMapping("/chkInsertForm")
//	public String chkInsertForm() {
//		return "redirect:/chkInser";
//	}
	
	@GetMapping("/")
	public String chkAbsent() {
		return "admin/check/chkAbsent";
	}
	
	
	

	/* 날짜별 반 출석 조회 */
//	@GetMapping("/chkViewClass")
//	public ModelAndView selectAllClassList(ModelAndView mv) throws Exception {
//		
//		List<CheckDTO> checkClassList = checkService.selectAllClassList();
//		
//		mv.addObject("checkClassList", checkClassList);
//		mv.setViewName("admin/check/chkViewClass");
//		
//		log.info("checkClassList : {}" , mv);
//		
//		return mv;
//	}

//	@RequestMapping(value = "/chkViewClass")
//	public ModelAndView selectAllClassList(@ModelAttribute ClassDTO clsId, @ModelAttribute CheckDTO chkDate, HttpServletRequest request, ModelAndView mv) throws Exception {
//		
//		String searchCls = request.getParameter("searchCls");
//		String datePicker = request.getParameter("datePicker");
//		
//		HashMap<String,String> paraMap = new HashMap<String,String>();
//		paraMap.put("searchCls", searchCls);
//		paraMap.put("datePicker", datePicker);
//		
//		List<CheckDTO> checkClassList = checkService.selectAllClassList(clsId, chkDate);
//		List<ClassDTO> classList = checkService.selectClassList(clsId);
//		
//		mv.addObject("checkClassList", checkClassList);
//		mv.addObject("searchCls", searchCls);
//		mv.addObject("datePicker", datePicker);
//		mv.addObject("classList", classList);
//		mv.setViewName("admin/check/chkViewClass");
//		
//		log.info("checkClassList : {}" , mv);
//		
//		
//		
//		return mv;
//	}
	
}
