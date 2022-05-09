package com.scon.project.admin.student.controller;

import java.io.File;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scon.project.admin.board.model.dto.BoardDTO;
import com.scon.project.admin.student.model.dto.ParentsDTO;
import com.scon.project.member.model.dto.ProfileDTO;
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
		
		// 프로필 사진 등록시 수행할 파일업로드 메소드 호출
		ProfileDTO profile = null;
		if(file != null) {
			profile = fileupload(file);
		}
		
		// 1. 회원 등록
		int result = memberService.insertMember(member, profile, role);

		// 2. 학생추가정보 등록 (학교명, 상담내용이 있을 경우)
		if(result > 0) {

			if(!student.getSchoolName().isEmpty() || !student.getConsult().isEmpty()) {
				int result2 = studentService.insertStudent(student);
			}
			
			// 3. 학부모 정보 등록 (학부모 성함이 있는 경우)
			if(!parents.getParentsName().isEmpty() ) {
				int result3 = studentService.insertParents(parents);
			}
		}
		
		return "/admin/student/studentList";
	}

	/* 파일 등록용 메소드 */
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
	public ModelAndView findStudentList(ModelAndView mv) {
		
		List<MemberDTO> memberList = memberService.findAllStudentList();
		
		mv.addObject("memberList", memberList);
		mv.setViewName("admin/student/studentList");
		
		return mv;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/findStudentById", method= RequestMethod.POST)
	public ModelAndView findStudentById(@RequestParam(value="id") String id, ModelAndView mv) {
		
		StudentDTO student = studentService.findStudentById(id);
		
		mv.addObject("student", student);
		mv.setViewName("admin/student/studentList");
		
		return mv;
	}
	
	
	
}

