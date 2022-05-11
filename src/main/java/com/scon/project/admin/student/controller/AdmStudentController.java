package com.scon.project.admin.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scon.project.admin.student.model.dto.ParentsDTO;
import com.scon.project.admin.student.model.dto.StudentDTO;
import com.scon.project.admin.student.model.service.StudentService;
import com.scon.project.member.controller.MemberController;
import com.scon.project.member.model.dto.MemberDTO;
import com.scon.project.member.model.dto.ProfileDTO;
import com.scon.project.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequestMapping("/admin")
public class AdmStudentController {
	
	private StudentService studentService;
	private MemberService memberService;
	private ObjectMapper objMapper;
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Autowired
	public AdmStudentController(StudentService studentService, MemberService memberService, ObjectMapper objMapper, BCryptPasswordEncoder passwordEncoder) {
		this.studentService = studentService;
		this.memberService = memberService;
		this.objMapper = objMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping("/studentRegist")
	public String studentRegistForm() {
		return "/admin/student/studentRegist";
	}
	
	@ResponseBody
	@PostMapping(value="/studentRegist")
	public Map<String, Object> insertStudent(@RequestParam Map<String, Object> param, MultipartFile file, ModelAndView mv) throws IOException {

		log.info("회원 확인 : {}" , param.get("member"));		
		log.info("학생 확인 : {}" , param.get("student"));
		log.info("학부모 확인 : {}" , param.get("parents"));

		String mem = (String) param.get("member");
		String stu = (String) param.get("student");
		String par = (String) param.get("parents");
		
		// Map으로 전달받은 값 ObjectMapper 사용하여 DTO 객체로 매핑
		MemberDTO member = objMapper.readValue(mem, MemberDTO.class);
		StudentDTO student = objMapper.readValue(stu, StudentDTO.class); 
		ParentsDTO parents = objMapper.readValue(par, ParentsDTO.class);
		
		// 비밀번호 암호화 처리
		String encodePwd = passwordEncoder.encode(member.getPassword());
		member.setPassword(encodePwd);
		
		log.info("멤버 DTO : {}" , member);
		log.info("학생 DTO : {}" , student);
		log.info("학부모 DTO : {}" , parents);
		
		// 학생 권한 번호
		int role = 1;
		
		// 1. 회원 등록
		int result = memberService.insertMember(member, role);

		// 2. 학생추가정보 등록 (학교명, 상담내용이 있을 경우)
		if(result > 0) {

			if(!student.getStudentType().isEmpty() || !student.getConsult().isEmpty()) {
				
				int result2 = studentService.insertStudent(student);
			}
			
			// 3. 학부모 정보 등록 (학부모 성함이 있는 경우)
			if(!parents.getParentsName().isEmpty() ) {
				
				int result3 = studentService.insertParents(parents);
			}
		}
		
		// 프로필 사진 등록시 수행할 파일업로드 메소드 호출
		ProfileDTO profile = null;
		if(file != null) {
			profile = MemberController.fileupload(file);
			int result4 = memberService.insertProfile(profile, member);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("location", "/admin/studentList");
		map.put("message", "원생 정보를 등록했습니다.");
		
		return map;
	}


	@GetMapping("/studentList")
	public ModelAndView findStudentList(ModelAndView mv) {
		
		List<MemberDTO> memberList = memberService.findAllStudentList();
		
		mv.addObject("memberList", memberList);
		mv.setViewName("/admin/student/studentList");
		
		return mv;
		
	}
	
	@ResponseBody
	@PostMapping("/findStudentById")
	public StudentDTO findStudentById(@RequestParam("id") String id) {

		StudentDTO student = studentService.findStudentById(id);
		
		return student;
	}
	
	@ResponseBody
	@PostMapping("/updateStudent")
	public Map<String, Object> updateStudent(@RequestParam Map<String, Object> param, MultipartFile file) throws IOException {
		
		String mem = (String) param.get("member");
		String stu = (String) param.get("student");
		String par = (String) param.get("parents");
		
		// Map으로 전달받은 값 ObjectMapper 사용하여 DTO 객체로 매핑
		MemberDTO member = objMapper.readValue(mem, MemberDTO.class);
		StudentDTO student = objMapper.readValue(stu, StudentDTO.class); 
		ParentsDTO parents = objMapper.readValue(par, ParentsDTO.class);
		
		/*
		 * MemberDTO member1 = memberService.selectMember(member.getId());
		 * 
		 * // 비밀번호 암호화 처리 if(!passwordEncoder.matches(member.getPassword(),
		 * member1.getPassword())) { String encodePwd =
		 * passwordEncoder.encode(member.getPassword()); member.setPassword(encodePwd);
		 * System.out.println("패스워드 암호화 처리 성공!"); };
		 */

		// 1. 회원 정보 수정
		int result = memberService.updateMember(member);

		// 2. 학생추가정보 수정 (학생타입, 상담내용이 있을 경우)
		if(result > 0) {
			
			int result2 = studentService.updateStudent(student);
			
			// 3. 학부모 정보 수정 (학부모 성함이 있는 경우)
			if(!parents.getParentsName().isEmpty() ) {
				int result3 = studentService.updateParents(parents);
			}
		}
		
		// 프로필 사진 수정
		ProfileDTO profile = null;
		if(file != null) {
			profile = MemberController.fileupload(file);
			int result4 = memberService.updateProfile(profile, member);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("location", "/admin/studentList");
		map.put("message", "원생 정보를 수정했습니다.");
		
		return map;
	}
	
}

