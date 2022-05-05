package com.scon.project.admin.student.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scon.project.admin.student.model.dto.ParentsDTO;
import com.scon.project.admin.student.model.dto.ProfileDTO;
import com.scon.project.admin.student.model.dto.StudentDTO;
import com.scon.project.admin.student.model.service.StudentService;
import com.scon.project.member.model.dto.MemberDTO;
import com.scon.project.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequestMapping("/admin")
public class AdmStudentController {
	
	private StudentService studentService;
	private MemberService memberService;
	private MessageSource messageSource;
	private ObjectMapper objMapper;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public AdmStudentController(StudentService studentService, MemberService memberService ,MessageSource messageSource, ObjectMapper objMapper, BCryptPasswordEncoder passwordEncoder) {
		this.studentService = studentService;
		this.memberService = memberService;
		this.messageSource = messageSource;
		this.objMapper = objMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping("/studentRegist")
	public String studentRegistForm() {
		return "/admin/student/studentRegist";
	}
	
	@PostMapping(value="/studentRegist")
	public String insertStudent(@RequestParam Map<String, Object> param, MultipartFile file) throws IOException {

//		log.info("회원 확인 : {}" , param.get("member"));
//		log.info("학생 확인 : {}" , param.get("student"));
//		log.info("학부모 확인 : {}" , param.get("parents"));

		String stu = (String) param.get("student");
		String mem = (String) param.get("member");
		String par = (String) param.get("parents");
		
		MemberDTO member = objMapper.readValue(mem, MemberDTO.class);
		StudentDTO student = objMapper.readValue(stu, StudentDTO.class); 
		ParentsDTO parents = objMapper.readValue(par, ParentsDTO.class);
		
		log.info("student DTO : {}" , student);
		
		
		// 비밀번호 암호화 처리 
		String encodePwd = passwordEncoder.encode(member.getPassword());
		member.setPassword(encodePwd);
		log.info("멤버 DTO : {}" , member);
		
		int result = memberService.insertMember(member);
		int result1 = studentService.insertStudent(student);
		int result2 = studentService.insertParents(parents);
		
		if(file != null) {
			ProfileDTO profile = fileupload(file);
			int result3 = studentService.insertFile(profile);
			
			if(result3 ==1) {
				int result4 = studentService.insertProfile(member.getId());
			}
			
		}

		return "/admin/student/studentList";
		
	}

	
	public ProfileDTO fileupload(MultipartFile file) {
		
		ProfileDTO profile = new ProfileDTO();
		
		/* 파일을 저장할 경로 설정 */
		String absolutePath = new File("").getAbsolutePath() + "\\src\\main\\resources\\static\\img\\profile";
		
		System.out.println("absolutePath : " + absolutePath);
		
		File mkdir = new File(absolutePath);
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		/* 파일명 변경 처리 */
		String originFileName = file.getOriginalFilename();
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		String fileType = file.getContentType();
		
		/* 파일을 저장한다. */
		try {
			file.transferTo(new File(absolutePath + "\\" + savedName));

			profile.setFileOrginName(originFileName);
			profile.setFileSaveName(savedName);;
			profile.setFilePath(absolutePath);
			profile.setFileType(fileType);
			
			
		} catch (IllegalStateException | IOException e) {
			System.out.println("파일 업로드 실패");
		}

		return profile;
	}
	


	@GetMapping("/studentList")
	public String studentList() {
		
		return "/admin/student/studentList";
	}
	
	
}

