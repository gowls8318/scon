package com.scon.project.myprofile.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scon.project.member.controller.MemberController;
import com.scon.project.member.model.dto.MemberDTO;
import com.scon.project.member.model.dto.ProfileDTO;
import com.scon.project.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j	
@Configuration
@RequestMapping("/")
public class MyProfileController {
	
		private MemberService memberService;
		private ObjectMapper objMapper;
		
		@Autowired
		public MyProfileController(MemberService memberService,  ObjectMapper objMapper) {
			this.memberService = memberService;
			this.objMapper = objMapper;
		}
		
		@GetMapping("/admin/myprofile")
		public String admMyprofileForm() {
			return "admin/myprofile/admMyprofile";
		}
		
		@GetMapping("/student/myprofile")
		public String stdMyprofileForm() {
			return "student/myprofile/stdMyprofile";
		}
		
		@ResponseBody
		@PostMapping("/studnet/modifyMyprofile")
		public Map<String,Object> modufyMyProfileStudent(@RequestParam Map<String, Object> param, MultipartFile file) throws JsonMappingException, JsonProcessingException {
			
			// 1. 회원 정보 수정
			String mem = (String) param.get("member");

			MemberDTO member = objMapper.readValue(mem, MemberDTO.class);
			
			int result = memberService.updateMember(member);
			
			log.info("수정프로필 확인 : {} ", member);
			
			// 프로필 사진 수정
			ProfileDTO profile = null;
			if(file != null) {
				profile = MemberController.fileupload(file);
				int result2 = memberService.updateProfile(profile, member);
			}
			
			Map<String, Object> map = new HashMap<>();
			if(result > 0 ) {
				map.put("message", "내 정보를 수정했습니다.");
				map.put("location", "/student");
			}else {
				map.put("message", "내정보 수정에 실패했습니다.");
			}
			
			return map;
		}
		
		@ResponseBody
		@PostMapping("/admin/modifyMyprofile")
		public Map<String,Object> modufyMyProfileTeacher(@RequestParam Map<String, Object> param, MultipartFile file) throws JsonMappingException, JsonProcessingException {

			String mem = (String) param.get("member");

			MemberDTO member = objMapper.readValue(mem, MemberDTO.class);
			// 1. 회원 정보 수정
			int result = memberService.updateMember(member);
			
			Map<String, Object> map = new HashMap<>();
			if(result > 0) {
				map.put("message", "내 정보를 수정했습니다.");
				map.put("location", "/admin");
			}else {
				map.put("message", "내정보 수정에 실패했습니다.");
			}
			
			return map;
		}
		
}
