package com.scon.project.admin.consultant.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.admin.consultant.model.service.ConsultantService;
import com.scon.project.common.paging.Criteria;
import com.scon.project.common.paging.Pagination;
import com.scon.project.member.model.dto.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class ConsultantController {

	private ConsultantService consultantService;
	private MessageSource messageSource;
	
	@Autowired
	public ConsultantController(ConsultantService consultantService, MessageSource messageSource) {
		this.consultantService = consultantService;
		this.messageSource = messageSource;
	}
	
	/* 상담 신청 내역 조회용 */
	@GetMapping("/consultant/hopeList")
	public ModelAndView selectAllConsultantHopeList(@ModelAttribute Criteria cri, ModelAndView mv) throws Exception {
		
		List<ConsultantDTO> consultantHopeList = consultantService.selectAllConsultantHopeList(cri);
//		log.info("consultantHopeList : {}", consultantHopeList);
		mv.addObject("consultantHopeList", consultantHopeList);
		
		int totalHope = consultantService.totalHope(cri);
		Pagination page = new Pagination(cri, totalHope);
		mv.addObject("page", page);
		
		mv.setViewName("admin/consultant/hopeList");
		
		return mv;
	}
	
	/* 상담 신청 상세 조회용 */
	@GetMapping("/consultant/hopeDetail")
	public String selectHopeDetailPage(@RequestParam int no, Model model) throws Exception {
		
		model.addAttribute("consultantHopeDetail", consultantService.selectConsultantHopeDetail(no));
		
		return "admin/consultant/hopeDetail";
	}
	
	/* 상담 일지 내역 조회용 */
	@GetMapping("/consultant/list")
	public ModelAndView selectAllConsultantList(@ModelAttribute Criteria cri, ModelAndView mv) throws Exception {
		
		List<ConsultantDTO> consultantList = consultantService.selectAllConsultantList(cri);
//		log.info("consultantList : {}", consultantList);
		mv.addObject("consultantList", consultantList);
		
		int total = consultantService.total(cri);
		Pagination page = new Pagination(cri, total);
		mv.addObject("page", page);
		
		mv.setViewName("admin/consultant/list");
		
		return mv;
	}
	
	/* 상담 일지 등록용 */
	@GetMapping("/consultant/insertForm")
	public String insertConsultantPage(@RequestParam int no, Model model) throws Exception {
		
		model.addAttribute("consultant", consultantService.selectConsultantHopeDetail(no));
		
		return "admin/consultant/insertForm";
	}
	
	@PostMapping("/consultant/insertForm")
	public String insertConsultant(@ModelAttribute ConsultantDTO con, RedirectAttributes rttr, Locale locale, @AuthenticationPrincipal UserImpl user) throws Exception {
		
//		log.info("로그인 유저 : {} ", user);
//		con.setMemberId(user.getId());
		
		consultantService.insertConsultant(con);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("insertConsultant", null, locale));
		
		return "redirect:/admin/consultant/hopeList";
	}
	
	/* 상담 일지 상세 조회용 */
	@GetMapping("/consultant/detail")
	public String selectDetailPage(@RequestParam int no, Model model) throws Exception {
		
		log.info("게시글 번호 : {}", no);
		
		model.addAttribute("consultantDetail", consultantService.selectConsultantDetail(no));
		
		return "admin/consultant/detail";
	}
	
	/* 상담 일지 수정용 */
	@GetMapping("/consultant/updateForm")
	public String modifyConsultantPage(@RequestParam int no, Model model) throws Exception {
		
		model.addAttribute("consultant", consultantService.selectConsultantDetail(no));
		
		return "admin/consultant/updateForm";
	}
	
	@PostMapping("/consultant/updateForm")
	public String modifyConsultant(@ModelAttribute ConsultantDTO con, RedirectAttributes rttr, Locale locale) throws Exception {
		
		consultantService.modifyConsultant(con);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("updateConsultant", null, locale));
		
		return "redirect:/admin/consultant/list";
	}
	
	/* 상담 일지 삭제용 */
	@PostMapping("/consultant/delete")
	public String deleteConsultant(@RequestParam int no, RedirectAttributes rttr, Locale locale) throws Exception {
		
		consultantService.deleteConsultant(no);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("deleteConsultant", null, locale));
		
		return "redirect:/admin/consultant/list";
	}
	
}
