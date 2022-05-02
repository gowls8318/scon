package com.scon.project.admin.taskBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/taskBoard")
public class TaskBoardController {

	@GetMapping("/insertTask")
	public String insertTask() {
		return "/admin/taskBoard/insertTask";
	}
	
	@GetMapping("/taskBoardList")
	public String taskBoardList() {
		return "admin/taskBoard/taskBoardList";
	}
	
	
	
	
	
}


