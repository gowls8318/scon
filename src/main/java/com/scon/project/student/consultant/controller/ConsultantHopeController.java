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
	
	@GetMapping("/consultant/consultantList")
	public ModelAndView selectAllConsultantList(ModelAndView mv) {
		
		List<ConsultantDTO> consultantList = consultantHopeService.selectAllConsultantList();
		
		mv.addObject("consultantList", consultantList);
		mv.setViewName("student/consultant/consultantList");
		
		return mv;
	}
	
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
		
		return "redirect:/student/consultant/consultantList";
	}
	
	@GetMapping("/consultant/consultantDetail/{conNo}")
	public ModelAndView selectDetailPage(@PathVariable int conNo, ModelAndView mv) {
		
		ConsultantDTO consultantDetail = consultantHopeService.selectConsultantDetail(conNo);
		
		mv.addObject("consultantDetail", consultantDetail);
		mv.setViewName("student/consultant/consultantDetail");
		
		return mv;
	}
	
	@GetMapping("/consultant/updateForm/{conNo}")
	public ModelAndView updateConsultantPage(@PathVariable int conNo, ModelAndView mv) {
		
		ConsultantDTO consultant = consultantHopeService.selectConsultantDetail(conNo);
		
		mv.addObject("consultant", consultant);
		mv.setViewName("student/consultant/updateForm");
		
		return mv;
	}
	
	@PostMapping("/consultant/updateForm/{conNo}")
	public String updateConsultant(@ModelAttribute ConsultantDTO con, RedirectAttributes rttr, Locale locale) throws Exception {
		
		consultantHopeService.modifyConsultant(con);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("updateConsultantHope", null, locale));
		
		return "redirect:/student/consultant/consultantList";
	}
	
}
