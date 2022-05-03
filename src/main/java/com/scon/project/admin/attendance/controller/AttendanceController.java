package com.scon.project.admin.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AttendanceController {
	
	@GetMapping("/attRegist")
	public String attRegist() {
		return "admin/attendance/attRegist";
	}
	
	@GetMapping("/attRegistNs")
	public String attRegistNoResult() {
		return "admin/attendance/attRegistNs";
	}
	
	@GetMapping("/attViewClass")
	public String attViewClass() {
		return "admin/attendance/attViewClass";
	}

	@GetMapping("/attViewClassNs")
	public String attViewClassNoResult() {
		return "admin/attendance/attViewClassNs";
	}
	
	@GetMapping("/attViewStudent")
	public String attViewStudent() {
		return "admin/attendance/attViewStudent";
	}
	
	@GetMapping("/attViewStudentNs")
	public String attViewStudentNoResult() {
		return "admin/attendance/attViewStudentNs";
	}
	
	@GetMapping("/attAbsent")
	public String attAbsent() {
		return "admin/attendance/attAbsent";
	}
	
	@GetMapping("/attAbsenttNs")
	public String attAbsentNoResult() {
		return "admin/attendance/attAbsentNs";
	}

}
