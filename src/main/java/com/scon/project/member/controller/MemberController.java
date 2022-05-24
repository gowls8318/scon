package com.scon.project.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.naming.StringManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scon.project.member.model.dto.MemberDTO;
import com.scon.project.member.model.dto.ProfileDTO;
import com.scon.project.member.model.dto.UserImpl;
import com.scon.project.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequestMapping("/member")
public class MemberController {
	
	private MemberService memberService;
	private static BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberController(MemberService memberService, BCryptPasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		MemberController.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping("/login") 
	public String memberLogin() { 
		return "/member/login"; 
	}
	
	@PostMapping("/login")
	public void loginForm(@RequestParam(required=false) String errorMessage, Model model) {
		model.addAttribute("errorMessage", errorMessage);
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
	public String newPwdForm() {
		return "/member/newPwd";
	}
	
	//비밀번호 찾기 => 비밀번호 변경
	@ResponseBody
	@PostMapping("/newPwd")
	public int newPwd(MemberDTO member) {
		
		String encodePwd = pwd_encoder(member.getPassword());
		member.setPassword(encodePwd);
		
		int result = memberService.updatePassword(member);

		return result;
	}

	//아이디 중복 체크
	@ResponseBody
	@PostMapping("/checkId")
	public int checkId(MemberDTO member) {
		
		int result = memberService.checkId(member);

		return result;
	}
	
	
	//비밀번호 변경
	@ResponseBody
	@PostMapping("/updatePwd")
	public int updatePwd(String pwd, String pwd2, String id) {

		int result = 0;
		
		String beforePwd = memberService.findPwdById(id);
		
		if(passwordEncoder.matches(pwd, beforePwd)){
			
			MemberDTO member = new MemberDTO();
			String encodePwd = pwd_encoder(pwd2);
			member.setId(id);
			member.setPassword(encodePwd);
			
			log.info("변경한 비밀번호 확인 : {} ", encodePwd);
			
			result = memberService.updatePassword(member);
			
		} 
		
		return result;
	}
	
	
	// 암호화 메소드
	public static String pwd_encoder(String pwd) {
		
		String encoderPwd = passwordEncoder.encode(pwd);
		
		return encoderPwd;
	}
	
	//비밀번호 찾기 이메일 전송 메소드
	@ResponseBody 
	@PostMapping("/authPwd")
	public int findPsssword(@RequestParam("name") String name, 
							@RequestParam("email") String email, 
							@RequestParam("id") String id) throws UnsupportedEncodingException, MessagingException {
		
		int result = 0;
		
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setName(name);
		member.setEmail(email);
		
		MemberDTO findMember = memberService.findPwd(member);
		log.info("findMember 확인 : {}" , findMember);
		
		Random r = new Random();
		int num = r.nextInt(999999); // 랜덤난수설정
		
		if(findMember != null) 
			
			result = sendMail(findMember.getEmail(), num); // 이메일 발송 메소드 호출 
		
		return result;
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

	public int sendMail(String memberEmail, int num) throws UnsupportedEncodingException, MessagingException {
		int result = 0;
				
		String from ="scon.email.test@gmail.com";
		String fromName = "Scon";
		String to = memberEmail;
		
		String host = "smtp.gmail.com";
		int port = 587;
		
		String smtp_username = "scon.email.test@gmail.com";
		String smtp_password = "scontest1234";
		 
		String title = "[SCON] 비밀번호변경 인증 이메일 입니다";
		String body = String.join(
		        System.getProperty("line.separator"),
		        "<h1>[SCON] 비밀번호 찾기(변경)</h1>",
		        System.getProperty("line.separator"),
		        "<h3>인증번호는 " + num + "입니다.</h3>",
		        "<p>비밀번호 찾기 페이지에서 인증번호를 입력해주세요.</p>."
		    );
		
		 Properties props = System.getProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.port", port); 
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        Session session = Session.getDefaultInstance(props);
	        MimeMessage msg = new MimeMessage(session);
	        
			msg.setFrom(new InternetAddress(from, fromName));
	        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        msg.setSubject(title);
	        msg.setContent(body, "text/html;charset=euc-kr");
	        
	        Transport transport = session.getTransport();
	        
	        try {
	            log.info("Sending...");
	            
	            transport.connect(host, smtp_username, smtp_password);
	            transport.sendMessage(msg, msg.getAllRecipients());
	            
	            log.info("Sending success!");
	            result = num; // 결과로 인증 번호를 반환
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            result = -1; //이메일 전송 실패시 결과 -1 반환
	            
	        } finally {
	            transport.close();
	        }
			return result;
	}
	
}
