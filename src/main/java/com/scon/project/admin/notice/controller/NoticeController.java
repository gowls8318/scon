package com.scon.project.admin.notice.controller;

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

import com.scon.project.admin.notice.model.dto.NoticeDTO;
import com.scon.project.admin.notice.model.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class NoticeController {

	private NoticeService noticeService;
	private MessageSource messageSource;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;

	}
//	등록된 공지사항 목록 조회
	@GetMapping("notiTables")
	public ModelAndView findNoticeList(ModelAndView mv) {

		List<NoticeDTO> noticeList = noticeService.findAllNoticeList();

		mv.addObject("noticeList", noticeList);
		mv.setViewName("admin/notice/notiTables");

		return mv;

	}
	
	
//	공지 작성 페이지 이동
	@GetMapping("notiForm")
	public String registPage() {
		return "admin/notice/notiForm";
	}

//	공지 글 작성
	@PostMapping("notiForm")
	public String registNotice(@ModelAttribute NoticeDTO notice, RedirectAttributes rttr, Locale locale) throws Exception {

		noticeService.registNotice(notice);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("registNotice", null, locale));
		
		return "redirect:/admin/notice/notiTables";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	
//	@GetMapping("/notiForm")
//	public String noticeForm() {
//		
//		return "admin/notice/notiForm";
//	}
//	
//	@GetMapping("/notiDetail")
//	public String noticeDetail() {
//		
//		return "admin/notice/notiDetail";
//	}	
//	
}
