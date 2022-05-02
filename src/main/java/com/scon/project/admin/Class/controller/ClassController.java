package com.scon.project.admin.Class.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ClassController {

		
		@GetMapping("/classRegist")
		public String getDashBoard2() {
			
			return "admin/class/insertClass";
		}
		
		@GetMapping("/classList")
		public String getDashBoard3() {
			
			return "admin/class/selectClass";
		}
		
		@GetMapping("/classUpdate")
		public String getDashBoard4() {
			
			return "admin/class/updateClass";
		}
		

	}
