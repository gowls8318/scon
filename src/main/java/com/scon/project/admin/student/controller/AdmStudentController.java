package com.scon.project.admin.student.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scon.project.admin.student.model.service.StudentService;
import com.scon.project.member.model.dto.MemberDTO;

@Configuration
@RequestMapping("/admin")
public class AdmStudentController {
	
	private StudentService studentService;
	private MessageSource messageSource;
	
	@Autowired
	public AdmStudentController(StudentService studentService, MessageSource messageSource) {
		this.studentService = studentService;
		this.messageSource = messageSource;
	}
	
	@GetMapping("/studentRegist")
	public String studentRegistForm() {
		return "admin/student/studentRegist";
	}
	
	@PostMapping("/studentRegist")
	public String insertStudent() {
		
		return "redirect:/admin/studentList";
	}
	
	
	@GetMapping("/studentList")
	public String studentList() {

		return "admin/student/studentList";
	}
	
	
	@PostMapping("/single-file")
	public String singleFileupload(@RequestParam MultipartFile singleFile, HttpServletRequest request, Model model) {
		
		System.out.println("singleFile : " + singleFile);
		
		/* 파일을 저장할 경로 설정 */
		String root = request.getSession().getServletContext().getRealPath("resources");
		
		System.out.println("root : " + root);
		
		String filePath = root + "\\uploadFiles";
		
		File mkdir = new File(filePath);
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		/* 파일명 변경 처리 */
		String originFileName = singleFile.getOriginalFilename();
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		
		/* 파일을 저장한다. */
		try {
			singleFile.transferTo(new File(filePath + "\\" + savedName));
			model.addAttribute("message", "파일 업로드 성공!");
		} catch (IllegalStateException | IOException e) {
			model.addAttribute("message", "파일 업로드 실패!");
		}

		return "result";
	}
	


}
