package com.scon.project.admin.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.notice.model.dto.NoticeDTO;
import com.scon.project.admin.notice.model.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class NoticeController {

	private NoticeService noticeService;
	
	
	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
		
	}
	
	@GetMapping("notiTables")
	public ModelAndView findNoticeList(ModelAndView mv) {
		
		List<NoticeDTO> noticeList = noticeService.findAllNoticeList();
		
		mv.addObject("noticeList", noticeList);
		mv.setViewName("admin/notice/notiTables");
		
		return mv;
		
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
