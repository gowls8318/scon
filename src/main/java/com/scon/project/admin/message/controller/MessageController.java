package com.scon.project.admin.message.controller;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import lombok.extern.slf4j.Slf4j;

@Configuration
@RequestMapping("/admin")
public class MessageController {
	
	@ResponseBody
	@PostMapping("/sendMessage")
	public int message ( @RequestParam(name = "phoneArr[]", required = false) List<String> phoneArr,
						 @RequestParam(name = "from", required = false) String from, 
						 @RequestParam(name = "text", required = false) String text,
								ModelAndView mv) {
		
		int result = 0; 
		
		for(String phone : phoneArr){
			
			 	String api_key = "NCSPICZFM7URKIHI";
			    String api_secret = "LV23L1MCXNQ8NUJBMYEGZC9CFWKWQTZ4";
			    Message coolsms = new Message(api_key, api_secret);

			    // 4 params(to, from, type, text) are mandatory. must be filled
			    HashMap<String, String> params = new HashMap<String, String>();
			    params.put("to", phone);	// 수신전화번호
			    params.put("from", from);	// 발신전화번호. 
			    params.put("type", "SMS");
			    params.put("text", text);
			    params.put("app_version", "test app 1.2"); //

			    try {
			      JSONObject obj = (JSONObject) coolsms.send(params);
			      System.out.println(obj.toString());
			      
			      result = 1; //문자 발송 성공
			      
			    } catch (CoolsmsException e) {
			      System.out.println(e.getMessage());
			      System.out.println(e.getCode());
			    }

		}
		
		return result;
	}
	
}
