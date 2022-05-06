package com.scon.project.student.consultant.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scon.project.admin.consultant.model.dto.ConsultantDTO;
import com.scon.project.student.consultant.model.service.ConsultantHopeService;

@Controller
@RequestMapping("/student")
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
	public ModelAndView selectAllConsultantList(ModelAndView mv) {
		
		List<ConsultantDTO> consultantList = consultantHopeService.selectAllConsultantList();
		
		mv.addObject("consultantList", consultantList);
		mv.setViewName("student/consultant/list");
		
		return mv;
	}
	
	/* 상담 신청 등록용 */
	@GetMapping("/consultant/insertForm")
	public String insertConsultantPage() {
		
		return "student/consultant/insertForm";
	}
	
	@PostMapping("/consultant/insertForm")
	public String insertConsultant(@ModelAttribute ConsultantDTO con, RedirectAttributes rttr, Locale locale) throws Exception {
		
		consultantHopeService.insertConsultant(con);
		
//		rttr.addFlashAttribute("successMessage", "상담 신청 등록이 완료되었습니다.");
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
	public String selectDetailPage(@RequestParam int no, Model model) {
		
		model.addAttribute("consultantDetail", consultantHopeService.selectConsultantDetail(no));
		
		return "student/consultant/detail";
	}
	
	/* 상담 신청 수정용 */
	@GetMapping("/consultant/updateForm")
	public String updateConsultantPage(@RequestParam int no, Model model) {
		
		model.addAttribute("consultant", consultantHopeService.selectConsultantDetail(no));
		
		return "student/consultant/updateForm";
	}
	
	@PostMapping("/consultant/updateForm")
	public String updateConsultant(@ModelAttribute ConsultantDTO con, RedirectAttributes rttr, Locale locale) throws Exception {
		
		consultantHopeService.modifyConsultant(con);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("updateConsultantHope", null, locale));
		
		return "redirect:/student/consultant/list";
	}
	
	/* 상담 신청 삭제용 */
	@PostMapping("consultant/delete")
	public String deleteConsultant(@RequestParam int no, RedirectAttributes rttr, Locale locale) {
		
		consultantHopeService.deleteConsultant(no);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("deleteConsultantHope", null, locale));
		
		return "redirect:/student/consultant/list";
	}
	
}
