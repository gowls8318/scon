package com.scon.project.admin.schedule.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scon.project.admin.schedule.model.dto.ScheduleDTO;
import com.scon.project.admin.schedule.model.service.ScheduleService;
import com.scon.project.member.model.dto.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	private ScheduleService scheduleService;
	private ObjectMapper objMapper;
	
	@Autowired
	public ScheduleController(ScheduleService scheduleService,ObjectMapper objMapper) {
		this.scheduleService = scheduleService;
		this.objMapper = objMapper;
	}
	
	// 스케쥴 조회
	@GetMapping("/schedule") 
	public String schedule() { 
		return "/admin/schedule/schedule"; 
	}
		
	@GetMapping("/allschedule")
    @ResponseBody
    public Map<String, Object> monthPlan(@AuthenticationPrincipal UserImpl user) {
	
        Map<String, Object> map = new HashMap<>();
 
        List<ScheduleDTO> sche = scheduleService.findSchedule(user);
        
        Date date = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        for(ScheduleDTO s : sche) {
        	if(!(s.getStartDay().equals(s.getEndDay()))) {
        		
        		System.out.println("시작일자와 종료일자가 다른경우");
        		
        		try {
					date = (Date) sdf.parse(s.getEndDay());
					
					System.out.println("before : "  + date);
					
					Calendar cal = Calendar.getInstance(); 
					cal.setTime(date);
					
					cal.add(Calendar.DATE, 1); //1일 더하기
					
					String strDate = sdf.format(cal.getTime());
					
					System.out.println("after : " + strDate);
					
					s.setEndDay(strDate);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
        		
        	}
        	
        }
        
        log.info("ScheduleDTO 조회 확인용 : {}", sche);
 
        map.put("schedule", sche);
        
        return map;
        
    }

	// 스케쥴 등록
	@ResponseBody
	@PostMapping("/schedule") 
	public int registSchedule(@ModelAttribute ScheduleDTO sche, @AuthenticationPrincipal UserImpl user)  { 
	
		log.info("memberId 확인 : {}", user.getId());
		
		sche.setMemberId(user.getId());
		log.info("일정 추가 DTO 확인용 : {}", sche);
		
		int result = scheduleService.registSchedule(sche);
		
		return result; 
	}
	
	// 스케쥴 수정
	@ResponseBody
	@PostMapping("/updateSchedule") 
	public int updateSchedule(@ModelAttribute ScheduleDTO sche, @AuthenticationPrincipal UserImpl user) { 
	
		log.info("memberId 확인 : {}", user.getId());
		
		sche.setMemberId(user.getId());
		log.info("일정 추가 DTO 확인용 : {}", sche);
		
		int result = scheduleService.updateSchedule(sche);
		
		return result; 
	}
	
	// 스케쥴 삭제
	@ResponseBody
	@PostMapping("/deleteSchedule") 
	public int deleteSchedule(@ModelAttribute ScheduleDTO sche, @AuthenticationPrincipal UserImpl user)  { 
		
		sche.setMemberId(user.getId());
		
		int result = scheduleService.deleteSchedule(sche);
		
		return result; 
	}

	
	/* 예외 처리 */
	@ExceptionHandler(value = Exception.class)
	public String exception(Exception e, Model model) {
		
		model.addAttribute("errorMessage", e.getMessage());
		
		return "common/adminError";
	}
	
//	@ResponseBody
//	@PostMapping("/schedule") 
//	public int registSchedule(@RequestParam Map<String, Object> param) throws JsonMappingException, JsonProcessingException { 
//		log.info("sche 확인용 : {}", param.get("sche"));
//		//int result = scheduleService.registSchedule(sche);
//		String str = (String) param.get("sche");
//		ScheduleDTO sche = objMapper.readValue(str, ScheduleDTO.class);
//		
//		log.info("ScheduleDTO 확인용 : {}", sche);
//		
//		return 0; 
//	}
	
	
}