package com.scon.project.member.controller;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scon.project.member.model.dto.MemberDTO;
import com.scon.project.member.model.dto.ProfileDTO;
import com.scon.project.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequestMapping("/member")
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/login") 
	public String memberLogin() { 
		return "/member/login"; 
	}
	
	@PostMapping("/login")
	public void loginForm(@RequestParam(required=false) String errorMessage, Model model) {
		model.addAttribute("errorMessage", errorMessage );
	}
	
	@GetMapping("/forgotId")
	public String forgetId() {
		return "/member/forgotId";
	}
	
	@ResponseBody
	@PostMapping("/forgotId")
	public String findIdByName(@RequestParam("name") String name, @RequestParam("email") String email) {
		
		String findId = memberService.findIdByName(name, email);
		
		String resultId = "";
		
		if(findId !=null) {
			
			int id_length = findId.length(); // 아이디의 총 길이
			
			resultId = findId.substring(0, 3);
			
			String answer = "";
			for (int j = 3; j < id_length; j++){
				answer += "*"; 
			}
			
			resultId += answer;
		}
		
		return resultId;
	}

	@GetMapping("/forgotPwd")
	public String forgetPwd() {
		return "/member/forgotPwd";
	}

	@GetMapping("/newPwd")
	public String newPwd() {
		return "/member/newPwd";
	}

	//아이디 중복 체크
	@ResponseBody
	@RequestMapping(value="/checkId", method= RequestMethod.POST)
	public int checkId(MemberDTO member) {
		
		int count = memberService.checkId(member);

		return count;
	}
	
	
	/* 파일 등록용 메소드 */
	public static ProfileDTO fileupload(MultipartFile file) {
		
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
	
	@ResponseBody
	@PostMapping("/deleteMember")
	public Map<String, Object> deleteMember(@RequestParam("id") String id, @RequestParam("status") String status){
		
		int result = memberService.deleteMember(id, status);
		
		Map<String, Object> map = new HashMap<>();
		
		if(result > 0) {
			if(status.equals("퇴직")) {
				map.put("message", "강사를 퇴직 처리했습니다.");
			}else {
				map.put("message", "원생을 퇴원 처리했습니다.");
			}
		} else {
			map.put("message", "삭제 실패하였습니다.");
		}
		return map;
	}

}
