package com.scon.project.student.lecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.lecture.model.dto.LectureDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.common.paging.Pagination;
import com.scon.project.member.model.dto.UserImpl;
import com.scon.project.student.lecture.model.service.StudentLectureService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/student")
@Slf4j
public class StudentLectureController {

	private StudentLectureService studentLectureService;
	
	@Autowired
	public StudentLectureController(StudentLectureService studentLectureService) {
		this.studentLectureService = studentLectureService;
	}
	
	/* 수강 내역 조회용 */
	@GetMapping("/lecture/list")
	public ModelAndView selectAllLectureList(@ModelAttribute Criteria cri, ModelAndView mv, @AuthenticationPrincipal UserImpl user) throws Exception {
		
		cri.setMemberId(user.getId());
		log.info("접속한 userId : {}", cri.getMemberId());
		
		List<LectureDTO> lectureList = studentLectureService.selectAllLectureList(cri);
		log.info("수강 내역 : {}", lectureList);
		
		int total = studentLectureService.total(cri);
		log.info("criteria : {}", cri);
		Pagination page = new Pagination(cri, total);
		mv.addObject("page", page);
		log.info("page : {}", page);
		
		mv.addObject("lectureList", lectureList);
		mv.setViewName("student/lecture/list");
		
		return mv;
	}
	
	/* 환불 모달에 값 넣기 */
	@GetMapping("/lecture/refund")
	@ResponseBody
	public LectureDTO selectRefundDetail(@RequestParam int no) throws Exception {
		
		log.info("수강 번호 : {}", no);
		
		LectureDTO lectureDetail = studentLectureService.selectLectureDetail(no);
		
		log.info("수강 상세 조회 : {}", lectureDetail);
		
		return lectureDetail;
	}
	
	/* 예외 처리 */
	@ExceptionHandler(value = Exception.class)
	public String exception(Exception e, Model model) {
		
		model.addAttribute("errorMessage", e.getMessage());
		
		return "common/studentError";
	}
	
}
