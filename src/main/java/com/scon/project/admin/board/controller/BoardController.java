package com.scon.project.admin.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BoardController {

	@GetMapping("/brdTables")
	public String brdTables() {
		
		return "admin/board/brdTables";
	}	
	@GetMapping("/brdForm")
	public String brdForm() {
		
		return "admin/board/brdForm";
	}	
	@GetMapping("/brdDetail")
	public String brdDetail() {
		
		return "admin/board/brdDetail";
	}	
	
}
