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
import com.scon.project.admin.check.model.dto.CheckListDTO;
import com.scon.project.admin.check.model.service.CheckService;
import com.scon.project.admin.lecture.model.dto.LectureDTO;
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
	
	
	/* 출석부 조회 */
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
	
	/* 결석 학생 조회 */
	@GetMapping("/chkAbsent")
	public ModelAndView selectAllAbsentList(@ModelAttribute ClassDTO cls, @ModelAttribute CheckDTO chk, @ModelAttribute MemberDTO member, ModelAndView mv) throws Exception {
	
		log.info("checkClass : {}" , member.getName());
		
		List<CheckDTO> checkClassList = checkService.selectAllAbsentList(cls, chk, member);
		List<ClassDTO> classList = checkService.selectClassList(cls);
		
		mv.addObject("checkClassList", checkClassList);
		mv.addObject("clsId", cls.getClsId());
		mv.addObject("chkDate", chk.getChkDate());
		mv.addObject("name", member.getName());
		mv.addObject("classList", classList);
		mv.setViewName("admin/check/chkAbsent");
		
		log.info("checkClassList : {}" , checkClassList);
		
		
		
		return mv;
	}

	
	/* 출석 등록 명단 조회 */
	@GetMapping("/chkInsertForm")
	public ModelAndView selectChkList(@ModelAttribute ClassDTO cls, @ModelAttribute LectureDTO lec, @ModelAttribute MemberDTO member, @ModelAttribute CheckDTO chkDate, ModelAndView mv) throws Exception {
		
		//log.info("checkDate : {}", chkDate);
		
		List<LectureDTO> selectChkList = checkService.selectChkList(cls, lec, member);
		List<ClassDTO> classList = checkService.selectClassList(cls);
		
		mv.addObject("selectChkList", selectChkList);
		mv.addObject("clsId", cls.getClsId());
		mv.addObject("name", member.getName());
		mv.addObject("classList", classList);
		mv.addObject("chkDate", chkDate.getChkDate());
		mv.setViewName("admin/check/chkInsertForm");
		
		//log.info("checkClassList : {}" , selectChkList);
		
		return mv;
	}
	
	@PostMapping("/chkInsertForm")
	public String chkInsertForm(@ModelAttribute CheckListDTO chk) throws Exception {

		log.info("넘기는 값 : {}", chk);
		
		
		//checkService.insertChkList(chk);
		
		return "redirect:/admin/check/chkInsertForm";
	}
	
	
	

	/* 출석 등록 명단 수정 */
	@GetMapping("/chkUpdateForm")
	public ModelAndView selectUpdateList(@ModelAttribute ClassDTO cls, @ModelAttribute CheckDTO chk, @ModelAttribute MemberDTO member, ModelAndView mv) throws Exception {
	
		log.info("checkClass : {}" , member.getName());
		
		List<CheckDTO> checkClassList = checkService.selectAllClassList(cls, chk, member);
		List<ClassDTO> classList = checkService.selectClassList(cls);
		
		mv.addObject("checkClassList", checkClassList);
		mv.addObject("clsId", cls.getClsId());
		mv.addObject("chkDate", chk.getChkDate());
		mv.addObject("name", member.getName());
		mv.addObject("classList", classList);
		mv.setViewName("admin/check/chkUpdateForm");
		
		log.info("checkClassList : {}" , checkClassList);
		
		
		
		return mv;
	}
	
	/* 수강 수정용 */
	@PostMapping("/chkUpdateForm")
	public String updateChkList(@ModelAttribute CheckDTO chk) throws Exception {
		
		checkService.updateChkList(chk);
		
		return "redirect:/admin/check/chkUpdateForm";
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

//	
//	@GetMapping("/chkRegist")
//	public ModelAndView checkRegist(@ModelAttribute ClassDTO cls, @ModelAttribute CheckDTO chk, @ModelAttribute MemberDTO member, ModelAndView mv) throws Exception {
//		
//		List<CheckDTO> selectChkList = checkService.selectChkList(cls, chk, member);
//		
//		mv.addObject("selectChkList", selectChkList);
//		mv.addObject("clsId", cls.getClsId());
//		mv.addObject("chkDate", chk.getChkDate());
//		mv.addObject("name", member.getName());
//		mv.setViewName("admin/check/chkInsertForm");
//		
//		return mv;
//	}
	

	/* 날짜별 반 출석 조회 */
//	@GetMapping("/chkViewClass")
//	public ModelAndView selectAllClassList(ModelAndView mv) throws Exception {
//		
//		List<CheckDTO> checkClassList = checkService.selectAllClassList();
//	)	
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
