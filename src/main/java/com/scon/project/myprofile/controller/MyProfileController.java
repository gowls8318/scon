package com.scon.project.myprofile.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequestMapping("/")
public class MyProfileController {
		
		@GetMapping("/admin/myprofile")
		public String admMyprofileForm() {
			return "admin/myprofile/admMyprofile";
		}
		
		@GetMapping("/student/myprofile")
		public String stdMyprofileForm() {
			return "student/myprofile/stdMyprofile";
		}
			
		
}
