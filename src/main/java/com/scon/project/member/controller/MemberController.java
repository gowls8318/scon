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
			
			int id_length = findId.length(); // ???????????? ??? ??????
			
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
	
	//???????????? ?????? => ???????????? ??????
	@ResponseBody
	@PostMapping("/newPwd")
	public int newPwd(MemberDTO member) {
		
		String encodePwd = pwd_encoder(member.getPassword());
		member.setPassword(encodePwd);
		
		int result = memberService.updatePassword(member);

		return result;
	}

	//????????? ?????? ??????
	@ResponseBody
	@PostMapping("/checkId")
	public int checkId(MemberDTO member) {
		
		int result = memberService.checkId(member);

		return result;
	}
	
	
	//???????????? ??????
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
			
			log.info("????????? ???????????? ?????? : {} ", encodePwd);
			
			result = memberService.updatePassword(member);
			
		} 
		
		return result;
	}
	
	
	// ????????? ?????????
	public static String pwd_encoder(String pwd) {
		
		String encoderPwd = passwordEncoder.encode(pwd);
		
		return encoderPwd;
	}
	
	//???????????? ?????? ????????? ?????? ?????????
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
		log.info("findMember ?????? : {}" , findMember);
		
		Random r = new Random();
		int num = r.nextInt(999999); // ??????????????????
		
		if(findMember != null) 
			
			result = sendMail(findMember.getEmail(), num); // ????????? ?????? ????????? ?????? 
		
		return result;
	}
	
	
	/* ?????? ????????? ????????? */
	public static ProfileDTO fileupload(MultipartFile file) {
		
		ProfileDTO profile = new ProfileDTO();
		
		/* ????????? ????????? ?????? ?????? */
		String absolutePath = new File("").getAbsolutePath() + "\\src\\main\\resources\\static\\img\\profile";
		
		System.out.println("absolutePath : " + absolutePath);
		
		File mkdir = new File(absolutePath);
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		/* ????????? ?????? ?????? */
		String originFileName = file.getOriginalFilename();
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		String fileType = file.getContentType();
		
		/* ????????? ????????????. */
		try {
			file.transferTo(new File(absolutePath + "\\" + savedName));

			profile.setFileOrginName(originFileName);
			profile.setFileSaveName(savedName);;
			profile.setFilePath(absolutePath);
			profile.setFileType(fileType);
			
			
		} catch (IllegalStateException | IOException e) {
			System.out.println("?????? ????????? ??????");
		}

		return profile;
	}
	
	@ResponseBody
	@PostMapping("/deleteMember")
	public Map<String, Object> deleteMember(@RequestParam("id") String id, @RequestParam("status") String status){
		
		int result = memberService.deleteMember(id, status);
		
		Map<String, Object> map = new HashMap<>();
		
		if(result > 0) {
			if(status.equals("??????")) {
				map.put("message", "????????? ?????? ??????????????????.");
			}else {
				map.put("message", "????????? ?????? ??????????????????.");
			}
		} else {
			map.put("message", "?????? ?????????????????????.");
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
		 
		String title = "[SCON] ?????????????????? ?????? ????????? ?????????";
		String body = String.join(
		        System.getProperty("line.separator"),
		        "<h1>[SCON] ???????????? ??????(??????)</h1>",
		        System.getProperty("line.separator"),
		        "<h3>??????????????? " + num + "?????????.</h3>",
		        "<p>???????????? ?????? ??????????????? ??????????????? ??????????????????.</p>."
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
	            result = num; // ????????? ?????? ????????? ??????
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            result = -1; //????????? ?????? ????????? ?????? -1 ??????
	            
	        } finally {
	            transport.close();
	        }
			return result;
	}
	
}
