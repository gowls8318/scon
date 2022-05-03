package com.scon.project.admin.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class noticeController {

	@GetMapping("/notiTables")
	public String noticeTables() {
		
		return "admin/notice/notiTables";
	}	
	
	@GetMapping("/notiForm")
	public String noticeForm() {
		
		return "admin/notice/notiForm";
	}
	
	@GetMapping("/notiDetail")
	public String noticeDetail() {
		
		return "admin/notice/notiDetail";
	}	
	
}
