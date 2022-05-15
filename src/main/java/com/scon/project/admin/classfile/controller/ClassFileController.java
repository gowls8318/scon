package com.scon.project.admin.classfile.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.scon.project.admin.Class.dto.ClassDTO;
import com.scon.project.admin.Class.service.ClassService;
import com.scon.project.admin.classfile.dto.ClassFileDTO;
import com.scon.project.admin.classfile.service.ClassFileService;
import com.scon.project.member.model.dto.MemberDTO;
import com.scon.project.member.model.dto.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j // log테스트
@Controller
@RequestMapping("/admin")
public class ClassFileController {
	
	 private ClassFileService classFileService; 
	 private MessageSource messageSource;
	 
	 @Autowired 
	 public ClassFileController(ClassFileService classFileService, MessageSource messageSource) {
	 
	 this.classFileService = classFileService; 
	 this.messageSource = messageSource;
	 
	 }
	
	 //강의첨부자료 등록 (GET)
	 @GetMapping("/registClassFile")
	 public String registClassFile(ModelAndView mv) {
		 
		 // 여기에 mapper.xml에도 동일하게 있어야함
		 List<MemberDTO> memberList = classFileService.selectMember();
		 List<ClassDTO> classNameList = classFileService.selectClassName(); 
		
		 mv.addObject("memberList", memberList);
		 mv.addObject("classNameList", classNameList);
		 
		 log.info("classDTO : {} ", classNameList);
		 
		 return "/admin/classFile/insertClassFile";
	 }
	 
	 //강의첨부자료 등록(POST)
	 //@AuthenticationPrincipal UserImpl user : 넣는 이유 question
	 @PostMapping("/registClassFile")
	 public String classFileUpload(@RequestParam MultipartFile singleFile, @RequestParam int clsId, 
			 HttpServletRequest request, @ModelAttribute ClassFileDTO classFile, 
			 Model model) {
				
		 
			/* multipart로 전송 된 request에 대한 인코딩 처리를 해주어야 하는데 일반 인코딩 필터보다 구현하기 어려우므로
			 * 스프링에서 제공하는 인코딩 필터를 사용한다. (web.xml)*/
			String singleFileDescription = request.getParameter("singleFileDescription");
			System.out.println("singleFile " + singleFile);
			System.out.println("singleFileDescription : " + singleFileDescription);
			 
			/* 파일을 저장할 경로 설정 (프로젝트에 하나의 경로를 사용)*/
			String root = request.getSession().getServletContext().getRealPath("resources");
			
			System.out.println("root : " + root); //원하는것: 실제path
			
			String filePath = root + "\\uploadFiles"; //경로뒤에 "경로이름 붙임"
			
			File mkdir = new File(filePath); //1. 실제 존재하는 경로가 아니라면 exists를 물어보고
			if(!mkdir.exists()) {
				mkdir.mkdirs(); //2. 경로를 생성해준다
			}
			
			
			/*파일명을 규칙에 맞춰서 변경처리를 해준다*/
			String originFileName = singleFile.getOriginalFilename(); 
			//파일에 대한 정보 : 매개변수에 선언했던 mulifiletype에 파일로부터 얻어 올 수 있다.
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			// 보존해야하는것은 '.'뒤에오는 확장자 (.png인지 .pdf인지..) -> 올바르게 파일 해석 가능
			
			/*확장자 앞쪽 부분은 변경해주어야 한다*/
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			
			/*필요한 정보 Map에 담기*/
			Map<String, String> file = new HashMap<>();
			file.put("originFileName", originFileName);
			file.put("savedName", savedName);
			file.put("filePath", filePath);
		
			
			classFile.setFileOrginName(originFileName);
			classFile.setFileSaveName(savedName);
			classFile.setFilePath(filePath);
			classFile.setFileType(ext);
			classFile.setClsId(clsId);
			/* classFile.setClsName(clsName); */
			
			/* int result = ClassFileService.registClassFile */
			
			
			
			
			/* 파일을 서버 특정 위치에 저장한다 */
			try {
				singleFile.transferTo(new File(filePath + "\\" + savedName)); // 이게 바로 파일을 저장한다는 행위에 해당한다.
				model.addAttribute("message", "파일 업로드 성공!");
			} catch (IllegalStateException | IOException e) {
				model.addAttribute("message", "파일 업로드 실패!ㅜㅜ");
			}
			
			return "admin/classFile/classFileList"; //클래스파일리스트로 리턴한다.
		
		 
	 
	 }
	 
	 
	 

	
	 
	 

}
