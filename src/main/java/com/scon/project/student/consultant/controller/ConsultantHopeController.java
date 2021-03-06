package com.scon.project.student.consultant.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.common.paging.Criteria;
import com.scon.project.common.paging.Pagination;
import com.scon.project.member.model.dto.UserImpl;
import com.scon.project.student.consultant.model.service.ConsultantHopeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/student")
@Slf4j
public class ConsultantHopeController {

	private ConsultantHopeService consultantHopeService;
	private MessageSource messageSource;
	
	@Autowired
	public ConsultantHopeController(ConsultantHopeService consultantHopeService, MessageSource messageSource) {
		this.consultantHopeService = consultantHopeService;
		this.messageSource = messageSource;
	}
	
	/* 상담 신청 내역 조회용 */
	@GetMapping("/consultant/list")
	public ModelAndView selectAllConsultantList(@ModelAttribute Criteria cri, ModelAndView mv, @AuthenticationPrincipal UserImpl user) throws Exception {
		
		cri.setMemberId(user.getId());
		log.info("접속한 userId : {}", cri.getMemberId());
		
		List<ConsultantDTO> consultantList = consultantHopeService.selectAllConsultantList(cri);
		log.info("상담 신청 내역 : {}", consultantList);
		mv.addObject("consultantList", consultantList);
		
		int total = consultantHopeService.total(cri);
		log.info("criteria : {}", cri);
		Pagination page = new Pagination(cri, total);
		mv.addObject("page", page);
		log.info("page : {}", page);
		
		mv.setViewName("student/consultant/list");
		
		return mv;
	}
	
	/* 상담 신청 등록용 */
	@GetMapping("/consultant/insertForm")
	public String insertConsultantPage() {
		
		return "student/consultant/insertForm";
	}
	
	@PostMapping("/consultant/insertForm")
	public String insertConsultant(@ModelAttribute ConsultantDTO con, RedirectAttributes rttr, Locale locale, @AuthenticationPrincipal UserImpl user) throws Exception {
		
		log.info("로그인 유저 : {} ", user);
		con.setMemberId(user.getId());
		
		consultantHopeService.insertConsultant(con);
		
		/* MessageSource bean을 사용하려면 의존성 주입을 받아야 한다. */
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("insertConsultantHope", null, locale));
		
		return "redirect:/student/consultant/list";
	}
	
	/* 상담 신청 상세 조회용 */
//	@GetMapping("/consultant/detail/{no}")
//	public ModelAndView selectDetailPage(@PathVariable int no, ModelAndView mv) {
//		
//		ConsultantDTO consultantDetail = consultantHopeService.selectConsultantDetail(no);
//		
//		mv.addObject("consultantDetail", consultantDetail);
//		mv.setViewName("student/consultant/detail");
//		
//		return mv;
//	}
	
	@GetMapping("/consultant/detail")
	public String selectDetailPage(@RequestParam int no, Model model, @ModelAttribute Criteria cri) throws Exception {
		
		log.info("상담 신청 조회 번호 : {}", no);
		
		model.addAttribute("consultantDetail", consultantHopeService.selectConsultantDetail(no));
		
		/* 돌아가기 누를 시 현재 페이지로 이동하기 위함 */
		model.addAttribute("cri", cri);
		
		return "student/consultant/detail";
	}
	
	/* 상담 신청 수정용 */
	@GetMapping("/consultant/updateForm")
	public String updateConsultantPage(@RequestParam int no, Model model, @ModelAttribute Criteria cri) throws Exception {
		
		log.info("수정할 상담 신청 번호 : {}", no);
		
		model.addAttribute("consultant", consultantHopeService.selectConsultantDetail(no));
		
		/* 취소하기 누를 시 현재 페이지로 이동하기 위함 */
		model.addAttribute("cri", cri);
		
		return "student/consultant/updateForm";
	}
	
	@PostMapping("/consultant/updateForm")
	public String updateConsultant(@ModelAttribute ConsultantDTO con, RedirectAttributes rttr, Locale locale, @ModelAttribute Criteria cri) throws Exception {
		
		consultantHopeService.modifyConsultant(con);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("updateConsultantHope", null, locale));
		
		return "redirect:/student/consultant/list?pageNo=" + cri.getPageNo();
	}
	
	/* 상담 신청 삭제용 */
	@PostMapping("/consultant/delete")
	public String deleteConsultant(@RequestParam int no, RedirectAttributes rttr, Locale locale, @ModelAttribute Criteria cri) throws Exception {
		
		log.info("상담 신청 삭제 번호 : {}", no);
		
		consultantHopeService.deleteConsultant(no);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("deleteConsultantHope", null, locale));
		
		return "redirect:/student/consultant/list?pageNo=" + cri.getPageNo();
	}
	
	/* 예외 처리 */
	@ExceptionHandler(value = Exception.class)
	public String exception(Exception e, Model model) {
		
		model.addAttribute("errorMessage", e.getMessage());
		
		return "common/studentError";
	}
	
}
