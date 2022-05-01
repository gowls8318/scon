package com.scon.project.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
//혜진
//	@GetMapping("/notice")
//	public String getDashBoard() {
//		
//		return "admin/notice/notiTables";
//	}	
//
////소영
//	@GetMapping("/classRegist")
//	public String getDashBoard2() {
//		
//		return "admin/class/insertClass";
//	}
//	
	@GetMapping("/classList")
	public String getDashBoard3() {
		
		return "admin/class/selectClass";
	}
//	
//	@GetMapping("/classUpdate")
//	public String getDashBoard4() {
//		
//		return "admin/class/updateClass";
//	}
//	
	
//	
//	@GetMapping("/chat")
//	public String getDashBoard3() {
//		
//		return "admin/chat/chat";
//	}
//	
//	@GetMapping("/chatList")
//	public String getDashBoard4() {
//		
//		return "admin/chat/chatList";
//	}
	
//태미
//	@GetMapping("/studentList")
//	public String getDashBoard5() {
//		
//		return "admin/student/studentList";
//	}
//	
//	
////자연
//
//	@GetMapping("/gradeList")
//	public String main() {
//		return "admin/grade/gradeList";
//	}
//	
//	@GetMapping("/insertGrade")
//	public String test3() {
//		return "admin/grade/insertGrade";
//	}
//	
//	@GetMapping("/gradeRank")
//	public String test1() {
//		return "admin/grade/gradeRank";
//	}
//
//	@GetMapping("/insertTask")
//	public String test6() {
//		return "admin/taskBoard/insertTask";
//	}
//	@GetMapping("/taskBoardList")
//	public String test4() {
//		return "admin/taskBoard/taskBoardList";
//	}
//	@GetMapping("/progressList")
//	public String test5() {
//		return "admin/progress/progressList";
//	}
//	
//	
//	
//	
////연지
//	
//	@GetMapping("/admAccount")
//	public String test15() {
//		return "admin/account/admAccount";
//	}
//	
//	
//	
	
	
	
	
	
	
	
	
//수현
	@GetMapping("/academyInfo")
	public String test16() {
		return "admin/academy/academyInfo";
	}
	
}
