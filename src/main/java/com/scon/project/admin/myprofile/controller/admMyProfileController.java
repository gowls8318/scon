package com.scon.project.admin.myprofile.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequestMapping("/admin")
public class admMyProfileController {
		
		@GetMapping("/myprofile")
		public String admMyprofileForm() {
			return "admin/myprofile/admMyprofile";
		}
			
	
}
