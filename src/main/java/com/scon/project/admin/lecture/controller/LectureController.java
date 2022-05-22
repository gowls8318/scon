package com.scon.project.admin.lecture.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import com.scon.project.admin.lecture.model.dto.RefundDTO;
import com.scon.project.admin.lecture.model.service.LectureService;
import com.scon.project.common.paging.Criteria;
import com.scon.project.common.paging.Pagination;
import com.scon.project.member.model.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
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
	public ModelAndView selectAllLectureList(@ModelAttribute Criteria cri, ModelAndView mv) throws Exception {
		
		List<LectureDTO> lectureList = lectureService.selectAllLectureList(cri);
		log.info("수강 내역 : {}", lectureList);
		List<ClassDTO> classList = lectureService.selectAllClassList();
		
		int total = lectureService.total(cri);
		log.info("criteria : {}", cri);
		Pagination page = new Pagination(cri, total);
		mv.addObject("page", page);
		log.info("page : {}", page);
		
		mv.addObject("lectureList", lectureList);
		mv.addObject("classList", classList);
		mv.setViewName("admin/lecture/list");
		
		return mv;
	}
	
	/* 수강 등록용 - 강의 조회, 원생 조회 */
	@GetMapping("/lecture/insertForm")
	public ModelAndView selectAllClassList(ModelAndView mv) throws Exception {
		
		List<ClassDTO> classList = lectureService.selectAllClassList();
		log.info("강의 조회 : {}", classList);
		List<MemberDTO> memberList = lectureService.selectAllMemberList();
		log.info("원생 조회 : {}", memberList);
		
		mv.addObject("classList", classList);
		mv.addObject("memberList", memberList);
		mv.setViewName("admin/lecture/insertForm");
		
		return mv;
	}
	
	/* 수강 등록용 - 강의 조회 */
//	@GetMapping("/lecture/insertForm")
//	@ResponseBody
//	public LectureDTO selectLectureDetail(@RequestParam int no) throws Exception {
//		
//		LectureDTO lectureDetail = lectureService.selectLectureDetail(no);
//		
//		return lectureDetail;
//	}
	
	/* 수강 등록용 */
	@PostMapping("/lecture/insertForm")
	public String insertLecture(@ModelAttribute LectureDTO lec, RedirectAttributes rttr, Locale locale) throws Exception {
		
		log.info("수강 등록 : {}", lec);
		
		lectureService.insertLecture(lec);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("insertLecture", null, locale));
		
		return "redirect:/admin/lecture/list";
	}
	
	/* 수강 수정용 - 정보 조회 */
	@GetMapping("/lecture/updateForm")
	public String updateLecturePage(@RequestParam int no, Model model, @ModelAttribute Criteria cri) throws Exception {
		
		log.info("수정할 수강 번호 : {}", no);
		
		model.addAttribute("classList", lectureService.selectAllClassList());
		model.addAttribute("lecture", lectureService.selectLectureDetail(no));
		
		/* 취소하기 누를 시 현재 페이지로 이동하기 위함 */
		model.addAttribute("cri", cri);
		
		return "admin/lecture/updateForm";
	}
	
	/* 수강 수정용 */
	@PostMapping("/lecture/updateForm")
	public String updateLecture(@ModelAttribute LectureDTO lec, RedirectAttributes rttr, Locale locale, @ModelAttribute Criteria cri) throws Exception {
		
		lectureService.modifyLecture(lec);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("updateLecture", null, locale));
		
		return "redirect:/admin/lecture/list?pageNo=" + cri.getPageNo();
	}
	
	/* 수강 삭제용 */
	@PostMapping("/lecture/delete")
	public String deleteLecture(@RequestParam int no, RedirectAttributes rttr, Locale locale, @ModelAttribute Criteria cri) throws Exception {
		
		log.info("삭제 번호 : {}", no);
		
		lectureService.deleteLecture(no);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("deleteLecture", null, locale));
		
		return "redirect:/admin/lecture/list?pageNo=" + cri.getPageNo();
	}

	/* 환불 모달에 값 넣기 */
//	@GetMapping("/lecture/refund")
//	@ResponseBody
//	public LectureDTO selectRefundDetail(@RequestParam int no) throws Exception {
//		
//		LectureDTO lectureDetail = lectureService.selectLectureDetail(no);
//		
//		return lectureDetail;
//	}
	
	/* 환불 등록용 */
	@PostMapping("/lecture/registRefund")
	@ResponseBody
	public String insertRefund(@ModelAttribute RefundDTO ref, RedirectAttributes rttr, Locale locale) throws Exception {
		
		log.info("환불 정보 : {}", ref);
		
		lectureService.insertRefund(ref);
		
		return messageSource.getMessage("insertRefund", null, locale);
	}
	
	/* 예외 처리 */
	@ExceptionHandler(value = Exception.class)
	public String exception(Exception e, Model model) {
		
		model.addAttribute("errorMessage", e.getMessage());
		
		return "common/adminError";
	}
	
}
