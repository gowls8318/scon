package com.scon.project.admin.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ChatController {
	
	
	@GetMapping("/chat")
	public String getDashBoard3() {
		
		return "admin/chat/chat";
	}
	
	@GetMapping("/chatList")
	public String getDashBoard4() {
		
		return "admin/chat/chatList";
	}

}
