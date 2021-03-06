package com.scon.project.admin.teacher.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scon.project.admin.student.model.dto.ParentsDTO;
import com.scon.project.admin.student.model.dto.StudentDTO;
import com.scon.project.admin.teacher.model.dto.TeacherDTO;
import com.scon.project.admin.teacher.model.service.TeacherService;
import com.scon.project.member.controller.MemberController;
import com.scon.project.member.model.dto.MemberDTO;
import com.scon.project.member.model.dto.ProfileDTO;
import com.scon.project.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequestMapping("/admin")
public class admTeacherController {
	
	private TeacherService teacherService;
	private MemberService memberService;
	private ObjectMapper objMapper;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public admTeacherController(TeacherService teacherService, MemberService memberService, ObjectMapper objMapper, BCryptPasswordEncoder passwordEncoder) {
		this.teacherService = teacherService;
		this.memberService = memberService;
		this.objMapper = objMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping("/teacherRegist")
	public String teacherRegistForm() {
		return "/admin/teacher/teacherRegist";
	}
	
	@ResponseBody
	@PostMapping(value="/teacherRegist")
	public Map<String, Object> insertStudent(@RequestParam Map<String, Object> param, MultipartFile file, ModelAndView mv) throws IOException {

		log.info("?????? ?????? : {}" , param.get("member"));		
		log.info("?????? ?????? : {}" , param.get("teacher"));

		String mem = (String) param.get("member");
		String tch = (String) param.get("teacher");
	
		
		// Map?????? ???????????? ??? ObjectMapper ???????????? DTO ????????? ??????
		MemberDTO member = objMapper.readValue(mem, MemberDTO.class);
		TeacherDTO teacher = objMapper.readValue(tch, TeacherDTO.class); 
		
		// ???????????? ????????? ??????
		String encodePwd = passwordEncoder.encode(member.getPassword());
		member.setPassword(encodePwd);
		
		log.info("?????? DTO : {}" , member);
		log.info("?????? DTO : {}" , teacher);
		
		// ?????? ?????? ??????
		int role = 2;
		
		// 1. ?????? ??????
		int result = memberService.insertMember(member, role);

		// 2. ?????? ???????????? ??????
		if(result > 0) {
				int result2 = teacherService.insertTeacher(teacher);
		}
		
		// ????????? ?????? ????????? ????????? ??????????????? ????????? ??????
		ProfileDTO profile = null;
		if(file != null) {
			profile = MemberController.fileupload(file);
			int result4 = memberService.insertProfile(profile, member);
		}
		

		Map<String, Object> map = new HashMap<>();
		map.put("location", "/admin/teacherList");
		map.put("message", "?????? ????????? ??????????????????.");
		
		return map;
	}

	
	@GetMapping("/teacherList")
	public ModelAndView findStudentList(@ModelAttribute MemberDTO member, ModelAndView mv) {
		
		List<MemberDTO> memberList = memberService.findAllTeacherList(member);
		
		mv.addObject("memberList", memberList);
		mv.addObject("keyword", member.getKeyword());
		mv.addObject("name", member.getName());
		mv.setViewName("/admin/teacher/teacherList");
		
		return mv;
		
	}
	
	@ResponseBody
	@PostMapping("/findTeacherById")
	public TeacherDTO findStudentById(@RequestParam("id") String id, ModelAndView mv) {

		TeacherDTO teacher = teacherService.findTeacherById(id);
		
		return teacher;
	}
	
	@ResponseBody
	@PostMapping("/updateTeacher")
	public Map<String, Object> updateStudent(@RequestParam Map<String, Object> param, MultipartFile file, ModelAndView mv) throws IOException {
		
		String mem = (String) param.get("member");
		String tch = (String) param.get("teacher");
		
		// Map?????? ???????????? ??? ObjectMapper ???????????? DTO ????????? ??????
		MemberDTO member = objMapper.readValue(mem, MemberDTO.class);
		TeacherDTO teacher = objMapper.readValue(tch, TeacherDTO.class); 


		// 1. ?????? ?????? ??????
		int result = memberService.updateMember(member);

		// 2. ?????????????????? ??????
		if(result > 0) {
			int result2 = teacherService.updateTeacher(teacher);
		}
		
		// ????????? ?????? ??????
		ProfileDTO profile = null;
		if(file != null) {
			profile = MemberController.fileupload(file);
			int result3 = memberService.updateProfile(profile, member);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("location", "/admin/teacherList");
		map.put("message", "?????? ????????? ??????????????????.");
		
		return map;
	}
	
	
	
	

}
